package io.bet.betzilla.common

abstract class ExchangeSupervisor {
  val scheduler: Scheduler
  val marketGroup: List[MarketGroupSupervisor]
}
