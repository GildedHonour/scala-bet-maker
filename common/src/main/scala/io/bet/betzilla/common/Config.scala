package io.bet.betzilla.common

import com.typesafe.config.ConfigFactory

abstract class ConfigBase(currentConfigName: String) {

  private val conf = ConfigFactory.load

  protected val currentConf = conf getConfig currentConfigName

}

abstract class ConfigBaseExchange(currentConfigName: String) extends ConfigBase(currentConfigName) {
  val idLimit = currentConf getInt "marketIdsLimit"
  val marketIdsUrl = currentConf getString "marketIdsUrl"
  val marketDetailsUrlFormat = currentConf getString "marketDetailsUrlFormat"
}

object Config extends ConfigBase("common") {

}
