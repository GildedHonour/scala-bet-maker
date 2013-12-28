package io.bet.betzilla.betdaq

import io.bet.betzilla.common.MarketGroupPairId

class MarketGroupSupervisor(internalId: Int) extends io.bet.betzilla.common.MarketGroupSupervisor(internalId) {
  val urlFormat = Config.marketDetailsUrlFormat

  protected override def createPricer(mgIds: MarketGroupPairId) = new MarketGroupPricer(mgIds)
}
