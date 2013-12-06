package io.bet.betzilla.common

import akka.actor.Props
import io.bet.betzilla.betcore.MarketGroup
import akka.actor.Actor
import scala.concurrent._
import scala.concurrent.ExecutionContext.Implicits._
import scala.util._

abstract class MarketGroupPricer[T <: SessionMonitor](marketGroupId: Int) extends Actor {
  import MarketGroupPricer._

  val sessionA = context actorOf Props[T]

  def receive = {
    case Get => sessionA ! SessionMonitor.Get
    case SessionMonitor.Result(sessionId) =>
      context stop sessionA
      get(sessionId).onComplete {
        case Success(mg: MarketGroup) => sender ! mg
        case Failure(e) => // todo - what to do with e: log, send back, etc?
      }
  }

  def get(sessionId: String): Future[MarketGroup]
}

object MarketGroupPricer {
  object Get
  case class Result(marketGroup: MarketGroup)
}
