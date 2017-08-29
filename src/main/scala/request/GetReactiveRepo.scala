package request

import dispatch._, Defaults._
import scala.util.{ Success, Failure }
import akka.actor.ActorRef
import api._
import response._
import com.typesafe.scalalogging._

class GetReactiveRepo(httpWrapper: HttpWrapper) extends RequestHandler[String] with LazyLogging {

  val respProcessor = new GetGitHubDetails
  def processRequest(config: (String) => String): String = {
    val gitHubUri = config("protocol") + config("url_reactive");
    val posturi = url(gitHubUri).GET;
    val response = httpWrapper.sendRequest(posturi,0)
    response
    }

  def processGitHubResultAndGetAuthToken(response: String, config: (String) => String
                                         ): scala.collection.IndexedSeq[String] = {
        val processRsp = respProcessor.processResponse(config)
        val repos: scala.collection.IndexedSeq[String] = processRsp(response)
        repos
  }

}
