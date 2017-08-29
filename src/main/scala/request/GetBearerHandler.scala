package request

import dispatch._, Defaults._
import scala.util.{ Success, Failure }
import java.util.Base64
import java.nio.charset.StandardCharsets
import akka.actor.ActorRef
import api._
import response._
import com.typesafe.scalalogging._

case class GitTweets(tweets: List[(String, String, String, String)],
                     projectName: String)
                     
class GetBearerHandler(repos: scala.collection.IndexedSeq[String], httpWrapper: HttpWrapper) 
       extends RequestHandler[String] with LazyLogging {
  val accessHandler = new ParseAccessTokenResponse
  val rspP = new GetTweetDetails
  
  def processRequest(config: (String) => String): String = {
    val uri = config("protocol") + config("token_url");
    val posturi = url(uri).POST;
    val consumer_pass = config("consumer_key") + ":" + config("consumer_secret");
    val base64Encoded = Base64.getEncoder.encodeToString(consumer_pass.getBytes(StandardCharsets.UTF_8));
    val auth = "Basic " + base64Encoded;
    val params = Map("grant_type" -> "client_credentials")
    val headers = Map("Authorization" -> auth,
      "Content-Type" -> "application/x-www-form-urlencoded;charset=UTF-8");
    val finalReq = posturi << (params) <:< (headers)
    val response = httpWrapper.sendRequest(finalReq,0)
    response
    
  }

  def processRespAndCallTwitterSearch(response: String, config: (String) => String): Array[GitTweets] = {
  
        val numOfRequest = config("number_of_request").toInt    
        val listTweets = Array.ofDim[GitTweets](numOfRequest)
        val bearerResp = accessHandler.processResponse(config)
        val bearer = bearerResp(response)
        var i =0
        repos.map(x => {
          val twitterSearch = new GetTwitterSearchData(x, bearer, httpWrapper)
          val jsonrsp = twitterSearch.processRequest(config)
          listTweets(i) = jsonrsp
          i += 1
        })
        listTweets
 
  }
  
   


}
