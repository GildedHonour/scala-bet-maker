package io.bet.betzilla.betdaq.api

abstract class Request[T](val items: List[T])

//todo - read from config
class GetPricesRequest(
  items: List[Long],
  val thresholdAmount: Int = 0,
  val numberAgainstPricesRequired: Int = 3,
  val numberForPricesRequired: Int = 3
) extends Request(items)

class GetMarketInformationRequest(items: List[Long]) extends Request(items)

class PlaceOrdersNoReceiptRequestItem(
  val selectionId: Long,
  val stake: Int,
  val price: Double,
  val polarity: Byte,
  val expectedSelectionResetCount: Byte,
  val expectedWithdrawalSequenceNumber: Byte,
  val killType: Byte
)

class PlaceOrdersNoReceiptRequest(items: List[PlaceOrdersNoReceiptRequestItem]) extends Request(items)

class CancelAllOrdersRequest extends Request(Nil)

class CancelOrdersRequest(items: List[Long]) extends Request(items)

class CancelAllOrdersOnMarketRequest(items: List[Long]) extends Request(items)