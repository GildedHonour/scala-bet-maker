package io.bet.betzilla.betdaq.api

import scalaj.http.{Http, HttpOptions}
import scala.xml._
import scala.xml.NamespaceBinding

trait Service {

  val concretePostServiceTypeName: String

  def post(request: Elem, actionName: String): Http.Request =
  //todo - read from config
    Http.postData("http://api.betdaq.com/v2.0/" + concretePostServiceTypeName + ".asmx", request.toString())
      .header("Content-Type", "text/xml")
      .header("Charset", "UTF-8")
      .header("Content-Length", request.size.toString)
      .header("SOAPAction", "http://www.GlobalBettingExchange.com/ExternalAPI/" + actionName)
      .option(HttpOptions.readTimeout(10000))

  //todo - read from config
  def buildRequestXml(actionName: String, body: Elem): Elem = {
    val xml =
      <soap:Envelope
      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xmlns:xsd="http://www.w3.org/2001/XMLSchema"
      xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
        <soap:Header>
          <ExternalApiHeader
          version="2"
          languageCode="en"
          username="bogorman"
          password="ScalaRules123"
          xmlns="http://www.GlobalBettingExchange.com/ExternalAPI/"/>
        </soap:Header>
        <soap:Body>
          {Elem(
          null, actionName, null, NamespaceBinding(null, "http://www.GlobalBettingExchange.com/ExternalAPI/", TopScope),
          true, body
        )}
        </soap:Body>
      </soap:Envelope>

    import java.io.StringWriter

    val writer = new StringWriter
    XML write(writer, xml, "utf-8", xmlDecl = true, doctype = null)
    XML loadString writer.toString
  }

  protected def buildRequest(actionName: String, responseCreator: Http.Request => Response)(xml: Elem) = {
    //todo - 2 times actionName?
    val request = buildRequestXml(actionName, xml)
    responseCreator(post(request, actionName))
  }
}

trait ReadOnlyService extends Service {
  val concretePostServiceTypeName = "ReadOnlyService"
}


trait SecureService extends Service {
  val concretePostServiceTypeName = "Secure/SecureService"
}
