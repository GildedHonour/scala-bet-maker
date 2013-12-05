package io.bet.betzilla.common

import akka.actor.Actor
import io.bet.betzilla.betcore.MarketGroup

class MarketGroupPricer extends Actor {
  def receive = {

  }
}

object MarketGroupPricer {
  case class Get(marketId: Int)
  case class Result()
}
