package io.bet.betzilla.betdaq.api

class SecureServiceClient(header: ExternalApiHeader) extends SecureService {
  def placeOrdersNoReceipt(req: PlaceOrdersNoReceiptRequest) =
    buildRequest("PlaceOrdersNoReceipt", new PlaceOrdersNoReceiptResponse(_)) {
      <placeOrdersNoReceiptRequest>
        <Orders>
          { req.items map { x =>
          <Order
            SelectionId={ x.selectionId.toString }
            Stake={ x.stake.toString }
            Price={ x.price.toString }
            Polarity={ x.polarity.toString }
            ExpectedSelectionResetCount={ x.expectedSelectionResetCount.toString }
            ExpectedWithdrawalSequenceNumber={ x.expectedWithdrawalSequenceNumber.toString }
            CancelOnInRunning={ false.toString }
            CancelIfSelectionReset={ false.toString }
          />
          }}
        </Orders>
        <WantAllOrNothingBehaviour>{ false }</WantAllOrNothingBehaviour>
      </placeOrdersNoReceiptRequest>
    }

  def cancelAllOrders(req: CancelAllOrdersRequest) = buildRequest("CancelAllOrders", new CancelAllOrdersResponse(_)) {
    <cancelAllOrdersRequest>
      <cancelAllOrdersRequest />
    </cancelAllOrdersRequest>
  }

  def cancelOrders(req: CancelOrdersRequest) = buildRequest("CancelOrders", new CancelOrdersResponse(_)) {
    <cancelOrdersRequest>
      { req.items map (x => <OrderHandle>{ x }</OrderHandle>) }
    </cancelOrdersRequest>
  }

  def cancelAllOrdersOnMarket(req: CancelAllOrdersOnMarketRequest) = buildRequest(
    "CancelAllOrdersOnMarket", new CancelAllOrdersOnMarketResponse(_)) {
      <cancelAllOrdersOnMarketRequest>
        { req.items map (x => <MarketIds>{ x }</MarketIds>) }
      </cancelAllOrdersOnMarketRequest>
    }
}