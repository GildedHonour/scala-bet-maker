package io.bet.betzilla.betdaq

import io.bet.betzilla.betcore.MarketGroup
import org.json4s._
import org.json4s.native.JsonMethods._
import scalaj.http._
import io.bet.betzilla.common.MarketGroupPairId

class MarketGroupPricer(mgIds: MarketGroupPairId) extends
  io.bet.betzilla.common.MarketGroupPricerNoSession(mgIds) {

  def getSync: MarketGroup = {
    new MarketGroup
  }
}
