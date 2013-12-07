package io.bet.betzilla.betfair.api.json

import scala.concurrent.Future
import io.bet.betzilla.betcore.MarketGroup
import org.json4s._
import org.json4s.native.JsonMethods._
import scalaj.http._
import akka.actor.Props

class MarketGroupPricer(marketGroupId: Int) extends
  io.bet.betzilla.common.MarketGroupPricer[SessionMonitor](marketGroupId) {

  private val sessionA = context actorOf Props[SessionMonitor]
  private val connectionTimeout = 6000                          // todo
  private val readTimeout = 15000                               // todo
  private val url = Config.url // todo
  private val headers = {
    val contentTypeAndAccept = "application/json"
    Map(
      "X-Application" -> ConfigCommon.applicationKey,
      "Content-Type" -> contentTypeAndAccept,
      "Accept" -> contentTypeAndAccept
    )
  }

  private def buildHeaders = headers + ("X-Authentication" -> sessionId)

  def get(sessionId: String): Future[MarketGroup] = Future {
    val requestJson =
      s"""{
          "jsonrpc": "2.0",
          "method": "SportsAPING/v1.0/listMarketBook",
          "params": {
            "marketIds":["1.$marketGroupId"],
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

    val resultPlain = Http.postData(url, requestJson)
      .headers(buildHeaders.toList)
      .option(HttpOptions connTimeout connectionTimeout)
      .option(HttpOptions readTimeout readTimeout)
      .asString

    try {
      val marketsJson = (parse(resultPlain) \ "result" \\ classOf[JArray])(0).asInstanceOf[List[Map[String, String]]]


      val mg = new MarketGroup

      val result = marketsJson map { new Market(_) }
      println("result ok: " + result)
      result



    } catch {
      case e: Throwable =>
        println("Error: " + e.getMessage)
    }
  }
}
