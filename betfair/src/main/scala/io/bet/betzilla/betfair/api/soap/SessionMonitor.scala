package io.bet.betzilla.betfair.api.soap

import com.betfair.publicapi.types.global.v3.LoginReq
import com.betfair.publicapi.types.global.v3.LoginErrorEnum
import com.betfair.publicapi.v3.bfglobalservice.BFGlobalService_Service

class SessionMonitor extends io.bet.betzilla.common.SessionMonitor {

  protected def getSessionIdSync = {
    val loginReq = new LoginReq
    loginReq setUsername Config.login
    loginReq setPassword Config.password
    loginReq setProductId Config.productId

    println("------------api.soap.SessionMonitor.getSessionIdSync")
    val res1 = new BFGlobalService_Service()
    println("------------api.soap.SessionMonitor.getSessionIdSync: res1: " + res1)

    val res2 = res1.getBFGlobalService login loginReq
    println("------------api.soap.SessionMonitor.getSessionIdSync: res2: " + res2)

    val result = new BFGlobalService_Service().getBFGlobalService login loginReq
    println("------------api.soap.SessionMonitor.getSessionIdSync: result: " + result)

    result.getErrorCode match {
      case LoginErrorEnum.OK =>
        println("------betfair.api.soap.SessionMonitor.sessionId: " + result.getHeader.getSessionToken)
        result.getHeader.getSessionToken
      case _ =>
        println("------betfair.api.soap.SessionMonitor.sessionId error")
        "" // todo
    }
  }
}

