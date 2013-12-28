package io.bet.betzilla.common

import akka.actor.Props
import io.bet.betzilla.betcore.MarketGroup
import akka.actor.Actor
import scala.concurrent._
import scala.concurrent.ExecutionContext.Implicits._
import scala.util._
import scala.reflect.ClassTag

abstract class MarketGroupPricer(mgIds: MarketGroupPairId) extends Actor

abstract class MarketGroupPricerWithSession[T <: SessionMonitor : ClassTag](mgIds: MarketGroupPairId)
  extends MarketGroupPricer(mgIds) {

  import MarketGroupPricer._

  protected def get(sessionId: String) = Future {
    blocking {
      getSync(sessionId)
    }
  }

  protected def getSync(sessionId: String): MarketGroup

  val sessionA = context actorOf (Props[T], s"$this.session")

  def receive = {
    case Get =>
      println("--------MarketGroupPricer sessionA Get: SessionMonitor.Get: sessionA: " + sessionA)
      sessionA ! SessionMonitor.Get
    case SessionMonitor.Result(sessionId) =>
      println("--------MarketGroupPricer SessionMonitor.Result(sessionId): sessionId: " + sessionId)
      context stop sessionA
      get(sessionId) onComplete {
        case Success(mg: MarketGroup) => sender ! mg
        case Failure(e) => // todo - what to do with e: log, send back, retry, etc?
      }
  }

  override def toString = s"MarketGroupPricer;winMarketId:${mgIds.win};placeMarketId:${mgIds.place}"
}

abstract class MarketGroupPricerNoSession(mgIds: MarketGroupPairId) extends MarketGroupPricer(mgIds) {

  import MarketGroupPricer._

  protected def get = Future {
    blocking {
      getSync
    }
  }

  protected def getSync: MarketGroup

  def receive = {
    case Get =>
      get.onComplete {
        case Success(mg: MarketGroup) => sender ! mg
        case Failure(e) => // todo - what to do with e: log, send back, retry, etc?
      }
  }
}

object MarketGroupPricer {

  object Get

  case class Result(marketGroup: MarketGroup)

}