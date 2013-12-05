package io.bet.betzilla.common

import akka.actor.Actor

abstract class SessionMonitor extends Actor {
  import SessionMonitor._

  def receive = {
    case Get =>
      //get session id from the server and send it back
      val sessionId = getSessionId
      sender ! Result(sessionId)
  }

  protected def getSessionId: String // Future[String]? blocking { } ? ask the forum
}

object SessionMonitor {
  object Get
  case class Result(sessionId: String)
}
