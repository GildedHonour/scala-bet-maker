package io.bet.betzilla.test

import com.betfair.publicapi.types.global.v3.LoginReq
import com.betfair.publicapi.types.global.v3.LoginErrorEnum
import com.betfair.publicapi.v3.bfglobalservice.BFGlobalService_Service
import akka.actor.Actor
import scala.concurrent._
import scala.concurrent.ExecutionContext.Implicits._
import scala.util._

object Main extends App {
  val res1 = new BFGlobalService_Service()
  println(res1)

}

abstract class SessionMonitorBase extends Actor {

  import SessionMonitor._

  protected def getSessionIdSync: String

  protected def getSessionId = Future {
    blocking(getSessionIdSync)
  }

  def receive = {
    case GetAsync =>
      val s = sender
      println("receive.Get")
      getSessionId onComplete {
        case Success(sesId) => s ! Result(sesId)
        case Failure(e) => // todo: retry? log to a file? send back the error?
      }

    case GetSync =>
      val sesId = getSessionIdSync
      println("GetSync: " + sesId)
    //      sender ! Result(sesId)
  }
}

class SessionMonitor extends SessionMonitorBase {

  protected def getSessionIdSync = {
    val loginReq = new LoginReq
    loginReq setUsername "bogorman_bot"
    loginReq setPassword "BettingRules123"
    loginReq setProductId 82

    println("getSessionIdSync")
    val res1 = new BFGlobalService_Service()
    println("getSessionIdSync: res1: " + res1)

    val res2 = res1.getBFGlobalService login loginReq
    println("getSessionIdSync: res2: " + res2)

    val result = new BFGlobalService_Service().getBFGlobalService login loginReq
    println("getSessionIdSync: result: " + result)

    result.getErrorCode match {
      case LoginErrorEnum.OK =>
        println("sessionId: " + result.getHeader.getSessionToken)
        result.getHeader.getSessionToken
      case _ =>
        println("sessionId error")
        "" // todo
    }
  }
}

object SessionMonitor {

  object GetAsync

  object GetSync

  case class Result(sessionId: String)

}

class SessionMonitorNoActor {
  def getSessionIdSync = {
    val loginReq = new LoginReq
    loginReq setUsername "bogorman_bot"
    loginReq setPassword "BettingRules123"
    loginReq setProductId 82

    println("getSessionIdSync")
    val res1 = new BFGlobalService_Service()
    println("getSessionIdSync: res1: " + res1)

    val res2 = res1.getBFGlobalService login loginReq
    println("getSessionIdSync: res2: " + res2)

    val result = new BFGlobalService_Service().getBFGlobalService login loginReq
    println("getSessionIdSync: result: " + result)

    result.getErrorCode match {
      case LoginErrorEnum.OK =>
        println("sessionId: " + result.getHeader.getSessionToken)
        result.getHeader.getSessionToken
      case _ =>
        println("sessionId error")
        "" // todo
    }
  }
}
