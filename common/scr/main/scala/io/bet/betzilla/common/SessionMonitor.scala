package io.bet.betzilla.common

import akka.actor.Actor

class SessionMonitor extends Actor {
  import SessionMonitor._

  def receive = {
    case Get =>
      //get session id and send back
      val sessionId = ???
      //Result(sessionId)
  }
}

object SessionMonitor {
  object Get
  case class Result(sessionId: String)
}
