package request

import dispatch._, Defaults._
import scala.util.{ Success, Failure }
import akka.actor.ActorRef
import api._
import response._
import com.typesafe.scalalogging._

class GetTwitterSearchData(projectName: String, bearer: String, httpWrapper: HttpWrapper) 
      extends RequestHandler[GitTweets] with LazyLogging {

  val github = "github.com/"
  val bearStr = "Bearer "
  
  val details = new GetTweetDetails
  def processRequest(config: (String) => String): GitTweets = {
    val uri = config("protocol") + config("twitter_search");
    val geturi = url(uri).GET;
    val strvalue = github + projectName + " since:" + config("request_from")
    val params = List(("q" -> strvalue))
    val auth = bearStr + bearer
    val headers = Map("Authorization" -> auth)
    val finalReq = geturi <<? (params) <:< (headers)
    val response = httpWrapper.sendRequest(finalReq,0)
    val tweetRsp = details.processResponse(config)
    val tuple = tweetRsp(response)
    val tweet = new GitTweets(tuple, projectName)
    tweet
  }

}
