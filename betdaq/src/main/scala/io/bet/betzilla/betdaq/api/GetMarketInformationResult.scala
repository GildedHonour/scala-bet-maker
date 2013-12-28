package io.bet.betzilla.betdaq.api

class GetMarketInformationResult(elem: xml.Node) extends Result(elem) {
  val markets = (elem \\ "Markets").map(new Markets(_)).toList
  
  class Markets(elem: xml.Node) extends super.Markets(elem) {
    val eventClassifierId: Long = (elem\"@EventClassifierId").head.toString.toLong  
    val selections = (elem \\ "Selections").map(new Selections(_)).toList
  }
  
  class Selections(elem: xml.Node) extends super.Selections(elem) {
    val displayOrder: Byte = (elem\"@DisplayOrder").head.toString.toByte
  }
  
  override def toString = {
    val repr = new StringBuilder
    var fc = 0
    markets.foreach ( f => {
      fc+=1
      repr.append("{ Markets:[{\n")
      repr.append("\t\"Id\": "   + f.id + "\n")
      repr.append("\t\"Name\": \"" + f.name + "\"\n")
      repr.append("\t\"EventClassifierId: " + f.eventClassifierId + "\n")
      repr.append("\t\"InRunningDelaySeconds: " + f.inRunningDelaySeconds + "\n")
      repr.append("\t\"IsEnabledForMultiples: " + f.isEnabledForMultiples + "\n")
      repr.append("\t\"IsInRunningAllowed: " + f.isInRunningAllowed + "\n")
      repr.append("\t\"WithdrawalSequenceNumber: " + f.withdrawalSequenceNumber + "\n")
      repr.append("\t\"Selections: [")
      var pc = 0
      f.selections.foreach ( p => {
        pc+=1
        repr.append("\t{\n")
        repr.append("\t\t\"Id\": " + p.id + ",\n")  
        repr.append("\t\t\"Name\": \"" + p.name + "\",\n")    
        repr.append("\t\t\"Status\": " + p.status + ",\n")    
        repr.append("\t\t\"ResetCount\": " + p.resetCount + ",\n")     
        repr.append("\t\t\"DeductionFactor\": " + p.deductionFactor + ",\n")    
        repr.append("\t\t\"displayOrder\": " + p.displayOrder + "\n")    
        if(pc==f.selections.length) repr.append("\t}\n") else repr.append("\t},\n") // comma stripping
      })
      if(fc==markets.length) repr.append("}\n") else repr.append("},\n") // comma stripping
    })
    repr.append("]}")
    repr.toString
  }
}