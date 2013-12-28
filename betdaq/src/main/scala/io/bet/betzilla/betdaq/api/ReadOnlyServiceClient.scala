package io.bet.betzilla.betdaq.api

//todo - read from config
class ExternalApiHeader(username: String, languageCode: String = "en", version: Int = 2)

class ReadOnlyServiceClient(header: ExternalApiHeader) extends ReadOnlyService {

  def getMarketInformation(req: GetMarketInformationRequest) =
    buildRequest("GetMarketInformation", new GetMarketInformationResponse(_)) {
      <getMarketInformationRequest>
        { req.items map(x => <MarketIds>{ x }</MarketIds>) }
      </getMarketInformationRequest>
    }

  def getPrices(req: GetPricesRequest) = buildRequest("GetPrices", new GetPricesResponse(_)) {
    <getPricesRequest
      ThresholdAmount={req.thresholdAmount.toString}
      NumberForPricesRequire={req.numberForPricesRequired.toString}
      NumberAgainstPricesRequired={req.numberAgainstPricesRequired.toString}>
        { req.items map(x => <MarketIds>{ x }</MarketIds>) }
    </getPricesRequest>
  }
}