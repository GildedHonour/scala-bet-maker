package io.bet.betzilla.betdaq.api

import xml.Node

abstract class Result(val elements: Node) {
  val timestamp = elements \\ "Timestamp"
  val returnStatus = new ReturnStatus(elements \\ "ReturnStatus")
  
  class ReturnStatus(elem: xml.NodeSeq) {
    val callId = (elem\"@CallId").head.toString
    val description = (elem\"@Description").head.toString
    val code = (elem\"@Code").head.toString.toInt
  }
  
  class OrderHandle(elem: Node) {
    val get = elem.text.toLong
  }
  
  class Markets(node: Node) {
    val id = (node \ "@Id").head.toString.toLong
    val name = (node \ "@Name").head.toString
    val startTime = (node \ "@StartTime").head.toString
    val inRunningDelaySeconds = (node \ "@InRunningDelaySeconds").head.toString.toByte
    val displayOrder = (node \ "@DisplayOrder").head.toString.toByte
    val withdrawalSequenceNumber = (node \ "@WithdrawalSequenceNumber").head.toString.toByte
    val status = (node \ "@Status").head.toString.toByte
    val numberOfWinningSelections = (node \ "@NumberOfWinningSelections").head.toString.toByte
    val typeMarkets = (node \ "@Type").head.toString.toByte
    val isCurrentlyInRunning = (node \ "@IsCurrentlyInRunning").head.toString.toBoolean
    val isManagedWhenInRunning = (node \ "@IsManagedWhenInRunning").head.toString.toBoolean
    val isInRunningAllowed = (node \ "@IsInRunningAllowed").head.toString.toBoolean
    val isEnabledForMultiples = (node \ "@IsEnabledForMultiples").head.toString.toBoolean
    val isPlayMarket = (node \ "@IsPlayMarket").head.toString.toBoolean
  }
  
  class Selections(node: Node) {
    val id = (node \ "@Id").head.toString.toLong
    val name = (node \ "@Name").head.toString
    val status = (node \ "@Status").head.toString.toByte
    val resetCount = (node \ "@ResetCount").head.toString.toByte
    val deductionFactor = (node \ "@DeductionFactor").head.toString.toDouble
  }
}