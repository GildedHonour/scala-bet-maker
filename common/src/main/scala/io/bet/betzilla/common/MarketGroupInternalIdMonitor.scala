package io.bet.betzilla.common

import akka.actor.Actor
import scala.concurrent._
import scala.util._
import java.net.URL
import org.json4s._
import org.json4s.native.JsonMethods._
import ExecutionContext.Implicits.global
import akka.event.Logging

abstract class MarketGroupInternalIdMonitor extends Actor {

  import MarketGroupInternalIdMonitor._

  val log = Logging(context.system, this)

  val url: String

  val idLimit: Int

  private def getInternalIdList = Future {
    blocking(getInternalIdListSync)
  }

  private def getInternalIdListSync = {
    val inputStreamRaw = new URL(url).openConnection.getInputStream
    val inputStream = scala.io.Source fromInputStream inputStreamRaw
    val json = parse(inputStream getLines() mkString "\n")
    val jsonIds = json \ "ids" \\ classOf[JInt]
    jsonIds take idLimit map (_.toInt)
  }

  def receive = {
    case Get =>
      val s = sender
      getInternalIdList onComplete {
        case Success(idList) =>
          log info s"\n-------MarketGroupInternalIdMonitor Get Success: idList:\n ${idList mkString "\n"}"
          s ! Result(idList)

        case Failure(e) => // todo
      }
  }
}

object MarketGroupInternalIdMonitor {

  object Get

  case class Result(internalIdList: List[Int])

}