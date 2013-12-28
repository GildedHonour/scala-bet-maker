package io.bet.betzilla.betdaq.api

import scalaj.http.Http
import scala.xml.XML

abstract class Response(response: Http.Request) {
  protected val nodeName: String
  protected val resultNodeShortName: String

  private val responseXml = XML loadString response.asString
  protected val items = (responseXml \\ nodeName).toList

  val responseCode = response.responseCode
  val result = (responseXml \\ resultNodeShortName + "Result").toList
}

class GetPricesResponse(response: Http.Request) extends Response(response) {
  protected val nodeName = "MarketPrices"
  protected val resultNodeShortName = "GetPrices"
}

class GetMarketInformationResponse(response: Http.Request) extends Response(response) {
  protected val nodeName = "Markets"
  protected val resultNodeShortName = "GetMarketInformation"
}

class PlaceOrdersNoReceiptResponse(response: Http.Request) extends Response(response) {
  protected val nodeName = "Order"
  protected val resultNodeShortName = "PlaceOrdersNoReceipt"
}


class CancelOrdersResponse(response: Http.Request) extends Response(response) {
  protected val nodeName = "OrderHandle"
  protected val resultNodeShortName = "CancelOrders"
}

class CancelAllOrdersOnMarketResponse(response: Http.Request) extends Response(response) {
  protected val nodeName = "OrderIds"
  protected val resultNodeShortName = "CancelAllOrdersOnMarket"
}

class CancelAllOrdersResponse(response: Http.Request) extends Response(response) {
  protected val nodeName = "OrderHandle"
  protected val resultNodeShortName = "CancelAllOrders"
}

class CancelOrdersResponseItem

class CancelAllOrdersOnMarketResponseItem

class CancelAllOrdersResponseItem