package io.bet.betzilla.betdaq.api

import xml.Node

class PlaceOrdersNoReceiptResult(elem: Node) extends Result(elem){
  
  val orderHandles = (elem \\ "OrderHandle").map(new OrderHandle(_)).toList
  val orders = (elem \\ "Order").map(new Order(_)).toList
  
  class Order(elem: Node) {
    val orderHandle = (elem \ "@OrderHandle").head.toString.toLong
    val returnCode = (elem \ "@ReturnCode").head.toString.toInt
    val punterReferenceNumber = (elem\"@PunterReferenceNumber").head.toString.toLong 
  }
    
  override def toString = {
    val repr = new StringBuilder
    
    repr.append("{ Orders:[\n")
    var fc = 0
    orders.foreach ( f => {
      fc+=1
      repr.append("\t{\n")
      repr.append("\t\"OrderHandle\": "   + f.orderHandle + ",\n")
      repr.append("\t\"ReturnCode\": \"" + f.returnCode + "\",\n")
      repr.append("\t\"PunterReferenceNumber\": \"" + f.punterReferenceNumber + "\",\n")     
      if(fc==orders.length) repr.append("}\n") else repr.append("},\n") // comma stripping 
    })    
    repr.append("], OrderHandles:[\n")
    fc = 0
    orderHandles.foreach ( f => {
      fc+=1
      repr.append("\t{\n")
      repr.append("\t\"Id\": " + f.get + ",\n")
      if(fc==orders.length) repr.append("}\n") else repr.append("},\n") // comma stripping 
    })    
    repr.append("]}")
    repr.toString
  }
  
}