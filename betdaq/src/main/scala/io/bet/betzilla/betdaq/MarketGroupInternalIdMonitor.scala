package io.bet.betzilla.betdaq

class MarketGroupInternalIdMonitor extends io.bet.betzilla.common.MarketGroupInternalIdMonitor {

  val url = Config.marketIdsUrl

  val idLimit = Config.idLimit
}
