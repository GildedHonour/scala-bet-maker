package io.bet.betzilla.betfair.api.soap

import io.bet.betzilla.betcore.MarketGroup
import com.betfair.publicapi.types.exchange.v5.APIRequestHeader
import com.betfair.publicapi.types.exchange.v5.GetMarketPricesCompressedReq
import com.betfair.publicapi.v5.bfexchangeservice.BFExchangeService_Service
import com.betfair.publicapi.types.exchange.v5.GetMarketPricesErrorEnum
import io.bet.betzilla.common.MarketGroupPairId


class MarketGroupPricer(mgIds: MarketGroupPairId)
  extends io.bet.betzilla.common.MarketGroupPricerWithSession[SessionMonitor](mgIds) {

  def getSync(sessionId: String): MarketGroup = {
    val header = new APIRequestHeader
    header setClientStamp 0
    header setSessionToken sessionId
    val mpcReq = new GetMarketPricesCompressedReq
    mpcReq setHeader header

    val winPlaceMarketPrices = List(mgIds.win, mgIds.place) map {
      x =>
        mpcReq setMarketId x
        val port = new BFExchangeService_Service().getBFExchangeService
        val rawPrices = port getMarketPricesCompressed mpcReq
        rawPrices.getErrorCode match {
          case GetMarketPricesErrorEnum.OK => rawPrices.getMarketPrices
          case _ => Nil
        }
    }

    //todo
    winPlaceMarketPrices match {
      case x :: y :: _ => Some(x.toString, y.toString)
      case _ => None
    }

    new MarketGroup()
  }
}
