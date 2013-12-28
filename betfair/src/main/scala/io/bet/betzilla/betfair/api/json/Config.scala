package io.bet.betzilla.betfair.api.json

object Config extends io.bet.betzilla.common.ConfigBase("betfair.json") {

  val pricingUrl = currentConf getString "pricingUrl"

  val sessionUrl = currentConf getString "sessionUrl"

  val applicationKey = currentConf getString "applicationKey"
}
