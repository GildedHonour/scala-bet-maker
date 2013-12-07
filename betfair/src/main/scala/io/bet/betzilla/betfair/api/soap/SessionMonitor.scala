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
    val result = new BFGlobalService_Service().getBFGlobalService login loginReq

    result.getErrorCode match {
      case LoginErrorEnum.OK => result.getHeader.getSessionToken
      case _ => // todo
    }
  }
}

