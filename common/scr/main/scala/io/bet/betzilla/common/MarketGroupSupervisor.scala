package io.bet.betzilla.common

import akka.actor.Actor
import com.betmonster.betcore._

class MarketGroupSupervisor(internalId: Int) extends Actor {

  lazy val marketGroup: MarketGroup = {

  }

  def receive = {
    case SessionMonitor.Result(sessionId) =>
  }
}

object MarketGroupSupervisor {

}
