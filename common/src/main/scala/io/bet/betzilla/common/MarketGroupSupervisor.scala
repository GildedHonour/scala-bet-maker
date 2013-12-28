package io.bet.betzilla.common

import io.bet.betzilla.betcore.MarketGroup
import java.net.URL
import org.json4s._
import org.json4s.native.JsonMethods._
import akka.actor._
import akka.actor.Actor
import akka.actor.Props
import scala.io.Source
import com.fasterxml.jackson.databind.ObjectMapper
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import akka.event.Logging
import scala.util._

abstract class MarketGroupSupervisor(internalId: Int) extends Actor with Stash {

  import MarketGroupSupervisor._

  val urlFormat: String

  val log = Logging(context.system, this)

  protected def createPricer(mgIds: MarketGroupPairId): MarketGroupPricer

  private val marketGroup = Future {
    implicit val formats = org.json4s.DefaultFormats
    val fullUrl = urlFormat + internalId
    log info s"\n--------$this.marketGroup Future; fullUrl: $fullUrl"
    val inputStreamRaw = new URL(fullUrl).openConnection.getInputStream
    val inputStream = Source fromInputStream inputStreamRaw
    val json = parse(inputStream getLines() mkString "\n")
    val mapper = new ObjectMapper
    val res = mapper readValue(compact(render(json)), classOf[MarketGroup])
    log info s"\n---------marketGroup json marketGroup: winMarketId:${res.winMarketId()};placeMarketId:${res.placeMarketId()}"
    res
  }

  marketGroup onComplete {
    case Success(mg: MarketGroup) =>
      self ! InitDone(mg)
      log info s"\n\n--------$this.marketGroup onComplete Success"
    case Failure(e) => log info s"\n\n--------$this.marketGroup onComplete Failure: $e"
  }

  def receive = initReceive

  def initReceive: Receive = {
    case InitDone(mg: MarketGroup) =>
      unstashAll()
      context become normalReceive(mg)
      log info s"--------$this.initReceive InitDone"

    case _ => stash()
  }

  def normalReceive(mg: MarketGroup): Receive = {
    case GetLastPrices =>
      val wmId = mg.winMarketId()
      val pmId = mg.placeMarketId()
      val marketGroupPricerA = context actorOf (Props(createPricer(new MarketGroupPairId(wmId, pmId))), s"marketGroupPricer${wmId}_${pmId}")
      marketGroupPricerA ! MarketGroupPricer.Get
      log info s"\n------MarketGroupSupervisor_$internalId.GetLastPrices marketGroupPricerA: $marketGroupPricerA MarketGroupPricer.Get"

    case MarketGroupPricer.Result(marketGroup: MarketGroup) =>
  }

  override def toString = s"MarketGroupSupervisor_$internalId"
}

object MarketGroupSupervisor {

  case class InitDone(mg: MarketGroup)

  object GetLastPrices

}

case class MarketGroupPairId(win: Int, place: Int)