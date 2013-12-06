package io.bet.betzilla.betfair.api.json

import java.io.{InputStreamReader, BufferedReader}
import java.net.URL
import scala.concurrent.Future


class SessionMonitor extends io.bet.betzilla.common.SessionMonitor {

  protected val url = ??? //Config.bfApiJsonSessionUrl // todo

  protected def getSessionId = Future {
    val in = new BufferedReader(new InputStreamReader(new URL(url).openConnection.getInputStream))
    val result = in.readLine
    in.close()
    result
  }
}
