class ApiTest {
//  val header = new ExternalApiHeader("bogorman")
//  val client = new ReadOnlyServiceClient(header)
//	val secureClient = new SecureServiceClient(header)
//
//	// ####### TESTING ELEMENTS ############
//
//  val marketids = List[Long](3620896, 3620594)
//  val ordersToCancel = List[Long](2052363905, 2052363906)
//
//	val orderItem = List(
//	      new PlaceOrdersNoReceiptRequestItem(19697267, 2, 1.01, 2, 0, 1, 3),
//	      new PlaceOrdersNoReceiptRequestItem(19697269, 2, 1.01, 2, 0, 1, 3)
//	      )
//
//	// for multiple requests
//	// WithdrawalSequenceNumber often changes!
//	val orderItem1 = List(
//	      new PlaceOrdersNoReceiptRequestItem(19724209, 2, 1.01, 2, 0, 0, 3),
//	      new PlaceOrdersNoReceiptRequestItem(19724210, 2, 1.01, 2, 0, 0, 3)
//	      )
//	val orderItem2 = List(
//	      new PlaceOrdersNoReceiptRequestItem(19722825, 2, 1.01, 2, 0, 0, 3),
//	      new PlaceOrdersNoReceiptRequestItem(19722826, 2, 1.01, 2, 0, 0, 3)
//	      )
//
//	val ordersToCancel1 = List[Long](2054573325, 2054573326)
//	val ordersToCancel2 = List[Long](2054573327, 2054573328)
//
//	// ####### TEST METHODS OPERATION ############
//  //testgetMarketInformation
//  //testgetPrices
//	//testbet
//	//testbet_multiplemarkets
//  //testcancelallorders
//  //testcancelallordersonmarkets
//  //testcancelorders
//
//	// ####### TEST METHODS DECLARATION ############
//
//	def testgetMarketInformation {
//	  val reqMarketInfo = new GetMarketInformationRequest(marketids)
//	  val responseMarketInfo = client.getMarketInformation(reqMarketInfo)
//	  val results = new GetMarketInformationResult( responseMarketInfo.result.head )
//	  println(results.toString)
//	}
//
//	def testgetPrices {
//	  val reqPricesInfo = new GetPricesRequest(marketids)
//	  val responsePricesInfo = client.getPrices(reqPricesInfo)
//	  val results = new GetPricesResult( responsePricesInfo.result.head )
//	  println(results.toString)
//	}
//
//	def testcancelallorders {
//	  val reqCancelOrders = new CancelAllOrdersRequest
//	  val responseAllOrders = secureClient.cancelAllOrders(reqCancelOrders)
//	  val results = new CancelAllOrdersResult( responseAllOrders.result.head )
//	  println(results.toString)
//	}
//
//	def testcancelallordersonmarkets {
//	  val reqCancelOrders = new CancelAllOrdersOnMarketRequest(marketids)
//	  val responseAllOrdersOnMarkets = secureClient.cancelAllOrdersOnMarket(reqCancelOrders)
//	  val results = new CancelAllOrdersOnMarketsResult( responseAllOrdersOnMarkets.result.head )
//	  println(results.toString)
//
//	}
//	def testcancelorders {
//	  val reqCancelOrders = new CancelOrdersRequest(ordersToCancel1:::ordersToCancel2)
//	  val responseCancelOrders = secureClient.cancelOrders(reqCancelOrders)
//	  val results = new CancelOrdersResult( responseCancelOrders.result.head )
//	  println(results.toString)
//
//	}
//
//  def testbet {
//	  val reqPlaceOrders = new PlaceOrdersNoReceiptRequest(orderItem)
//	  val responsePlaceOrders = secureClient.placeOrdersNoReceipt(reqPlaceOrders)
//	  val results = new PlaceOrdersNoReceiptResult(responsePlaceOrders.result.head)
//	  println(results.toString)
//
//  }
//
//  def testbetmultiplemarkets {
//    // create requests
//    val requestsPlaceOrders = List(
//        new PlaceOrdersNoReceiptRequest(orderItem1),
//        new PlaceOrdersNoReceiptRequest(orderItem2)
//        )
//
//    var responsesPlaceOrders = List[PlaceOrdersNoReceiptResponse]()
//    // place bets foreach request
//    requestsPlaceOrders.foreach { responsesPlaceOrders ::= secureClient.placeOrdersNoReceipt(_) }
//
//    var results = List[PlaceOrdersNoReceiptResult]()
//	  responsesPlaceOrders.foreach { p => results ::= new PlaceOrdersNoReceiptResult(p.result.head) }
//
//    results.foreach { p => println( p.toString ) }
//
//  }
  
}

