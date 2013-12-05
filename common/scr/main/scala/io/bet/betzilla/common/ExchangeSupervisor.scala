package io.bet.betzilla.common

import akka.actor.Actor

abstract class ExchangeSupervisor extends Actor {
  import ExchangeSupervisor._

  val scheduler: Scheduler
  val marketGroup: List[MarketGroupSupervisor]

  def receive = {
    case Enable(internalMarketId: Int) =>
    case Disable(internalMarketId: Int) =>
  }
}

object ExchangeSupervisor {
  case class Enable(internalMarketId: Int)
  case class Disable(internalMarketId: Int)
}
