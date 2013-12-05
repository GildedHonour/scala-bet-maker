package io.bet.betzilla.common

import akka.actor.Actor

class MarketGroupInternalIdMonitor extends Actor {
  import MarketGroupInternalIdMonitor._

  def receive = {
    case Get =>
      //get internal ids from bet.io
      //return the result
      val internlaIdList = getInternalIdList()
      sender ! Result(internlaIdList) // tell or ask pattern?
      context stop self
  }

  private def getInternalIdList() = {
    //todo
    List(1, 2, 3)
  }
}

object MarketGroupInternalIdMonitor {
  object Get
  case class Result(internalIdList: List[Int])
}