package io.bet.betzilla.common

import akka.actor.Actor
import scala.concurrent._
import scala.concurrent.ExecutionContext.Implicits._
import scala.util._

abstract class SessionMonitor extends Actor {
  import SessionMonitor._

  protected def getSessionIdSync: String

  protected def getSessionId =  Future { blocking (getSessionIdSync) }

  def receive = {
    case Get =>
      getSessionId onComplete {
        case Success(sId) => sender ! Result(sId) // todo - check the response code ( == 2xx)
        case Failure(e) => // todo: retry? log to a file? send back the error?
      }
  }
}

object SessionMonitor {
  object Get
  case class Result(sessionId: String)
}
