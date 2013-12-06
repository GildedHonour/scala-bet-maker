package io.bet.betzilla.betfair.api.json

import scala.concurrent.Future
import io.bet.betzilla.betcore.MarketGroup

class MarketGroupPricer(marketGroupId: Int) extends
  io.bet.betzilla.common.MarketGroupPricer[SessionMonitor](marketGroupId) {

  def get(sessionId: String): Future[MarketGroup] = ???
}
