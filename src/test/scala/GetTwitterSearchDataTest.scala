
import org.scalamock.scalatest.MockFactory
import org.scalatest.FlatSpec
import request._
import akka.actor.ActorRef
import akka.actor.ActorSystem
import config._
import akka.testkit._
import org.mockito.Mockito._
import dispatch._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.collection.mutable.StringBuilder
import scala.io.Source

class GetTwitterSearchDataTest extends FlatSpec with MockFactory {

  "The GetTwitterSearchDataTest " should "send out http request with no exceptions " in {
    val httpWrapper = mock[HttpWrapper]
    val reactive = new GetTwitterSearchData("component/reactive", "AAAA", httpWrapper)
    implicit val system = ActorSystem()
    val config = new ConfigFile("src/main/resources/config.properties");
    val actref: ActorRef = spy(TestProbe().ref)
    var ecount: Int = 0
    val bufferedSource = Source.fromFile("src/test/resources/twitterdetails.json")
    val builder = new StringBuilder
    for (line <- bufferedSource.getLines) {
      builder.append(line)
    }
    //val response: Future[String] = Future { builder.toString }
    (httpWrapper.sendRequest _).expects(*,0).returning(builder.toString)
    try {
      reactive.processRequest(config.getConfig())
    } catch {
      case e: Exception => ecount = 1
    }
    assert(ecount == 0)
  }
}