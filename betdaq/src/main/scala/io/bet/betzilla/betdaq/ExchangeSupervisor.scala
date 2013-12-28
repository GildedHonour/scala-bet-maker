package io.bet.betzilla.betdaq

class ExchangeSupervisor extends io.bet.betzilla.common.ExchangeSupervisor[MarketGroupInternalIdMonitor] {
  val scheduler = ???

  val marketGroupSupervisorList = ??? //todo

  def marketGroupSupervisorConstruct(internalId: Int) = new MarketGroupSupervisor(internalId) // todo
}
