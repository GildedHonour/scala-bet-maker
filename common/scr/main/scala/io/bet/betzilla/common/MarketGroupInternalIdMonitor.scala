package io.bet.betzilla.common

import akka.actor.Actor
import akka.pattern.ask

class MarketGroupInternalIdMonitor extends Actor {
  import MarketGroupInternalIdMonitor._

  def receive = {
    case Get =>
      //get internal ids from bet.io
      //return the result
      val internlaIdList = ???
      sender ? Result(internlaIdList) // tell or ask pattern
  }
}

object MarketGroupInternalIdMonitor {
  object Get
  case class Result(internalIdList: List[Int])
}