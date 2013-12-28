package io.bet.betzilla.betfair.api.soap

import io.bet.betzilla.common.MarketGroupPairId

class MarketGroupSupervisor(internalId: Int) extends io.bet.betzilla.common.MarketGroupSupervisor(internalId) {
   val urlFormat = io.bet.betzilla.betfair.Config.marketDetailsUrlFormat

   def createPricer(mgIds: MarketGroupPairId) = new MarketGroupPricer(mgIds)
 }
