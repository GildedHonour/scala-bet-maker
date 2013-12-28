package io.bet.betzilla.betdaq.api

import xml.Node

class GetPricesResult(elem: Node) extends Result(elem) {
  val marketsPrices = (elem \\ "MarketPrices").map(new MarketPrices(_)).toList

  class MarketPrices(elem: Node) extends Markets(elem) {
    val selections = (elem \\ "Selections").map(new Selections(_)).toList
  }

  class Selections(elem: Node) extends super.Selections(elem) {
    val forSidePricesList = (elem \\ "ForSidePrices").map(new ForSidePrices(_)).toList
    val againstSidePricesList = (elem \\ "AgainstSidePrices").map(new AgainstSidePrices(_)).toList
  }

  abstract class SidePrices(elem: Node) {
    val stake = (elem \ "@Stake").head.toString().toDouble
    val price = (elem \ "@Price").head.toString().toDouble
  }

  class ForSidePrices(elem: Node) extends SidePrices(elem)

  class AgainstSidePrices(elem: Node) extends SidePrices(elem)

  override def toString = {
    val repr = new StringBuilder
    var fc = 0
    marketsPrices.foreach(f => {
      fc += 1
      repr.append("{ MarketPrices:[{\n")
      repr.append("\t\"Id\": " + f.id + ",\n")
      repr.append("\t\"Name\": \"" + f.name + "\",\n")
      repr.append("\t\"InRunningDelaySeconds\": " + f.inRunningDelaySeconds + ",\n")
      repr.append("\t\"IsEnabledForMultiples\": " + f.isEnabledForMultiples + ",\n")
      repr.append("\t\"Selections: [")
      var pc = 0
      f.selections.foreach(p => {
        pc += 1
        repr.append("\t{\n")
        repr.append("\t\t\"Id\": " + p.id + ",\n")
        repr.append("\t\t\"Name\": \"" + p.name + "\",\n")
        repr.append("\t\t\"Status\": " + p.status + ",\n")
        repr.append("\t\t\"ResetCount\": " + p.resetCount + ",\n")
        repr.append("\t\t\"DeductionFactor\": " + p.deductionFactor + ",\n")
        repr.append("\t\t\"ForSidePrices\": [")
        sidePricesList(repr, p.forSidePricesList)
        repr.append("\t\t\"AgainstSidePrices\": [")
        sidePricesList(repr, p.againstSidePricesList)
        repr.delete(repr.length - 2, repr.length - 1) // strip last comma
        if (pc == f.selections.length) repr.append("\t}\n") else repr.append("\t},\n") // comma stripping
      })
      if (fc == marketsPrices.length) repr.append("}\n") else repr.append("},\n") // comma stripping
    })
    repr.append("]")
    repr.toString
  }

  def sidePricesList(repr: StringBuilder, list: List[SidePrices]) {
    val len = list.length
    if (len > 0) {
      repr.append("\n")
      var kc = 0
      list.foreach(k => {
        kc += 1
        repr.append("\t\t\t{\n")
        repr.append("\t\t\t\"stake\": " + k.stake + ",\n")
        repr.append("\t\t\t\"price\": " + k.price + "\n")
        if (kc == len) repr.append("\t\t\t}\n") else repr.append("\t\t\t},\n") // comma stripping
      })
      repr.append("\t\t],\n")
    } else {
      repr.append("],\n")
    }
  }
}