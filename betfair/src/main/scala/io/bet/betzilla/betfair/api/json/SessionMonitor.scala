package io.bet.betzilla.betfair.api.json

import java.io.{InputStreamReader, BufferedReader}
import java.net.URL

class SessionMonitor extends io.bet.betzilla.common.SessionMonitor {

  protected def getSessionIdSync = {
    val in = new BufferedReader(new InputStreamReader(new URL(Config.sessionUrl).openConnection.getInputStream))
    val result = in.readLine
    in.close()
    println("---------api.json.SessionMonitor.getSessionIdSync result: " + result)
    result
  }
}
