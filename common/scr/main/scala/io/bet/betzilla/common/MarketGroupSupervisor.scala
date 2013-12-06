package io.bet.betzilla.common

import akka.actor.Actor
import io.bet.betzilla.betcore.MarketGroup

class MarketGroupSupervisor(internalId: Int) extends Actor {

  lazy val marketGroup: MarketGroup = {

  }


  def receive = {

  }
}

object MarketGroupSupervisor {
  case class GetLastPrices(marketGroupId: Int)
}
