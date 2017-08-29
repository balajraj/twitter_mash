import org.mockito.Mockito._
import akka.actor.ActorRef

import dispatch._
import org.scalatest.FlatSpec

import request._
import response._
import actor._
import scala.concurrent.ExecutionContext.Implicits.global
import config._

import org.scalamock.scalatest.MockFactory
import akka.actor.ActorSystem
import akka.testkit._
import com.typesafe.scalalogging._

class GetBearerHandlerTest extends FlatSpec with MockFactory with LazyLogging {

  "The GetBearerHandlerTest " should "send out http request with no exceptions " in {

    val a: scala.collection.immutable.IndexedSeq[String] = Vector("component/reactive", "nafg/reactive")
    val httpWrapper = new HttpWrapper
    val req = new GetBearerHandler(a, httpWrapper)
    implicit val system = ActorSystem()
    val config = new ConfigFile("src/main/resources/config.properties");
    val actref: ActorRef = spy(TestProbe().ref)
    var ecount: Int = 0
    try {
      req.processRequest(config.getConfig())
    } catch {
      case e: Exception => ecount = 1
    }
    assert(ecount == 0)

  }

  "The response " should "to processed and subsequent request send out" in {
    val a: scala.collection.immutable.IndexedSeq[String] = Vector("component/reactive", "nafg/reactive")
    val httpWrapper = new HttpWrapper
    val req = new GetBearerHandler(a, httpWrapper)
    val parse = new ParseAccessTokenResponse

    val result = """{"token_type":"bearer","access_token":"AAAAAAAAAAAAAAAAAAAAA"}"""
   // val response: Future[String] = Future { result }

    val config = new ConfigFile("src/main/resources/config.properties");

    implicit val system = ActorSystem()
    val actref: ActorRef = spy(TestProbe().ref)

    var ecount: Int = 0
    try {
      req.processRespAndCallTwitterSearch(result, config.getConfig())
    } catch {
      case e: Exception => ecount = 1
    }
    assert(ecount == 0)

  }
}