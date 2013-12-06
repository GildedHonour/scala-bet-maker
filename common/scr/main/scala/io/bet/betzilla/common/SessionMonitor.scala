package io.bet.betzilla.common

import akka.actor.Actor
import scala.concurrent._
import scala.concurrent.ExecutionContext.Implicits._
import scala.util._

abstract class SessionMonitor extends Actor {
  import SessionMonitor._

  protected val url: String
  protected def getSessionId: Future[String]

  def receive = {
    case Get =>
      getSessionId onComplete {
        case Success(sId) => sender ! Result(sId)
        case Failure(e) => // todo: retry? log to a file? send back the error?
      }
  }
}

object SessionMonitor {
  object Get
  case class Result(sessionId: String)
}
