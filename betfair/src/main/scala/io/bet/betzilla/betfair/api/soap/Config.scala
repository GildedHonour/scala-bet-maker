package io.bet.betzilla.betfair.api.soap

object Config extends io.bet.betzilla.common.ConfigBase("betfair.soap") {
  val login = currentConf getString "login"
  val password = currentConf getString "password"
  val productId = currentConf getInt "productId"
}