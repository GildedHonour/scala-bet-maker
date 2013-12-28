package io.bet.betzilla.betfair.api.json

import io.bet.betzilla.betcore.MarketGroup
import org.json4s._
import org.json4s.native.JsonMethods._
import scalaj.http._
import io.bet.betzilla.common.MarketGroupPairId

class MarketGroupPricer(mgIds: MarketGroupPairId) extends
io.bet.betzilla.common.MarketGroupPricerWithSession[SessionMonitor](mgIds) {

  private val connectionTimeout = 6000
  // todo - Config
  private val readTimeout = 15000
  // todo - Config
  private val url = Config.pricingUrl // todo - Config

  private val headers = {
    val contentTypeAndAccept = "application/json"
    Map(
      "X-Application" -> Config.applicationKey,
      "Content-Type" -> contentTypeAndAccept,
      "Accept" -> contentTypeAndAccept
    )
  }

  def getSync(sessionId: String): MarketGroup = {
    println("----------api.json.MarketGroupPricer.getSync, sessionId: " + sessionId)
    val allHeaders = headers + ("X-Authentication" -> sessionId)
    val requestJson =
      s"""{
          "jsonrpc": "2.0",
          "method": "SportsAPING/v1.0/listMarketBook",
          "params": {
            "marketIds":["1.${mgIds.win}", "1.${mgIds.place}"],
            "priceProjection":{
              "priceData":["EX_BEST_OFFERS"],
              "exBestOfferOverRides":{"bestPricesDepth":2,"rollupModel":"STAKE","rollupLimit":20},
              "virtualise":false,
              "rolloverStakes":false
            },

            "orderProjection":"ALL",
            "matchProjection":"ROLLED_UP_BY_PRICE"
           },

          "id": "1"
         }"""

    println(s"----------api.json.MarketGroupPricer.getSync, sending request; " +
      s"requestJson: $requestJson\nheaders: ${allHeaders.toList}")
    val resultPlain = Http.postData(url, requestJson)
      .headers(allHeaders.toList)
      .option(HttpOptions connTimeout connectionTimeout)
      .option(HttpOptions readTimeout readTimeout)
      .asString

    println("----------api.json.MarketGroupPricer.getSync, sending request done: " + resultPlain)
    val marketsJson = (parse(resultPlain) \ "result" \\ classOf[JArray])(0).asInstanceOf[List[Map[String, String]]]
    println("----------api.json.MarketGroupPricer.getSync, result: " + marketsJson)
    new MarketGroup //todo
  }
}
