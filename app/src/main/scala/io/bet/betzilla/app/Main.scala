package io.bet.betzilla.app

import akka.actor.ActorSystem
import akka.actor.Actor
import akka.actor.Terminated
import akka.actor.ActorLogging
import akka.actor.Props
import akka.actor.ActorRef
import scala.util.control.NonFatal
import io.bet.betzilla._
import io.bet.betzilla.common.ExchangeSupervisor

object Main {

  def main(args: Array[String]): Unit = {
    if (args.length != 1) {
      println("You need to provide exactly 1 argument: the class of the application supervisor actor {bfs, bfj or bqs}")
    } else {
      val system = ActorSystem("Main")
      try {
        val appClass = parseExchangeClass(args(0))
        val app = system actorOf (Props(appClass), "appExchangeSupervisor")
        app ! ExchangeSupervisor.Start
        val terminator = system actorOf (Props(classOf[Terminator], app), "appTerminator")
      } catch {
        case NonFatal(e) =>
          system.shutdown()
          throw e
      }
    }
  }

  def parseExchangeClass(name: String): Class[_ <: common.ExchangeSupervisor[_]] = name match {
    case "bfs" => classOf[betfair.api.soap.ExchangeSupervisor]
    case "bfj" => classOf[betfair.api.json.ExchangeSupervisor]
    case "bqs" => classOf[betdaq.ExchangeSupervisor]
    case _ => throw new IllegalArgumentException("You need to choose from {bfs, bfj or bqs}")
  }

  class Terminator(app: ActorRef) extends Actor with ActorLogging {
    context watch app

    def receive = {
      case Terminated(_) =>
        log info "Application supervisor has terminated, shutting down"
        context.system.shutdown()
    }
  }

}