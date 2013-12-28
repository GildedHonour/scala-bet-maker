package io.bet.betzilla.betfair.api.soap

import io.bet.betzilla.betfair.MarketGroupInternalIdMonitor

class ExchangeSupervisor extends io.bet.betzilla.common.ExchangeSupervisor[MarketGroupInternalIdMonitor] {
  // todo
  val scheduler = null

  val marketGroupSupervisorList = Nil

  def marketGroupSupervisorConstruct(internalId: Int) = new MarketGroupSupervisor(internalId)
}
