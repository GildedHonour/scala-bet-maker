package io.bet.betzilla.betdaq.api

import xml.Node

abstract class CancelResult(elem: Node, idTags: String = "OrderHandle") extends Result(elem) {
  val cancelledOrderHandles = (elem \\ idTags).map(new OrderHandle(_)).toList
  val orders = (elem \\ "Order").map(new Order(_)).toList

  class Order(elem: Node) {
    val orderHandle = (elem \ "@OrderHandle").head.toString.toLong
    val cancelledForSideStake = (elem \ "@cancelledForSideStake").head.toString.toDouble
    val punterReferenceNumber = (elem \ "@PunterReferenceNumber").head.toString.toByte
  }
  
  override def toString = {
    val repr = new StringBuilder
    
    repr.append("{ Orders:[\n")
    var fc = 0
    orders.foreach ( f => {
      fc+=1
      repr.append("\t{\n")
      repr.append("\t\"OrderHandle\": "   + f.orderHandle + ",\n")
      repr.append("\t\"ReturnCode\": \"" + f.cancelledForSideStake + "\",\n")
      repr.append("\t\"PunterReferenceNumber\": \"" + f.punterReferenceNumber + "\",\n")     
      if(fc==orders.length) repr.append("}\n") else repr.append("},\n")
    })    
    repr.append("], CancelledOrdersHandles:[\n")
    fc = 0
    cancelledOrderHandles.foreach ( f => {
      fc+=1
      repr.append("\t{\n")
      repr.append("\t\"Id\": " + f.get + ",\n")
      if(fc==orders.length) repr.append("}\n") else repr.append("},\n")
    })    
    repr.append("]}")
    repr.toString
  }
}

class CancelAllOrdersResult(elem: Node) extends CancelResult(elem)

class CancelAllOrdersOnMarketsResult(elem: Node) extends CancelResult(elem, "OrderIds")

class CancelOrdersResult(elem: Node) extends CancelResult(elem)

