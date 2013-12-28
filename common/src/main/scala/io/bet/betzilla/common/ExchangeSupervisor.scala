package io.bet.betzilla.common

import akka.actor.{Props, Actor}
import akka.event.Logging
import scala.reflect.ClassTag

abstract class ExchangeSupervisor[TMarketGroupInternalIdMonitor <: MarketGroupInternalIdMonitor : ClassTag] extends Actor {

  import ExchangeSupervisor._

  val log = Logging(context.system, this)

  val scheduler: Scheduler

  val marketGroupSupervisorList: List[MarketGroupSupervisor] // todo: var or mutable Map (or something)

  def marketGroupSupervisorConstruct(internalId: Int): MarketGroupSupervisor

  def receive = {
    case Start =>
      val internalIdA = context actorOf(Props[TMarketGroupInternalIdMonitor], "internalidMonitor")
      internalIdA ! MarketGroupInternalIdMonitor.Get
      log info "\n------ExchangeSupervisor Start"

    case MarketGroupInternalIdMonitor.Result(internalIdList: List[Int]) =>
      log info s"\n------MarketGroupInternalIdMonitor.Result internalIdList:\n ${internalIdList mkString "\n"}"
      val marketGroupListA = internalIdList take 1 map { x =>
         context actorOf(Props(marketGroupSupervisorConstruct(x)), "marketGroupSupervisor_" + x)
      }

      marketGroupListA take 1 foreach { _ ! MarketGroupSupervisor.GetLastPrices }

    case Enable(internalMarketId: Int) =>

    case Disable(internalMarketId: Int) =>
  }
}

object ExchangeSupervisor {

  object Start

  case class Enable(internalMarketId: Int)

  case class Disable(internalMarketId: Int)

}
