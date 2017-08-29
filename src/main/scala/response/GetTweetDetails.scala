package response

import api._
import scala.util.parsing.json._

/**
 * Processes the response from the twitter api for reading the tweets on
 * github project, generates a tuple as output. 
 */
class GetTweetDetails extends ResponseHandler[(String) => List[(String, String, String, String)]] {

  def processResponse(config: (String) => String) = (resp: String) => {
    val result: List[(String, String, String, String)] = for {
      Some(M(map)) <- List(JSON.parseFull(resp))
      L(tweets) = map("statuses")
      M(tweet) <- tweets
      S(date) = tweet("created_at")
      S(text) = tweet("text")
      M(user) = tweet("user")
      S(name) = user("name")
      S(location) = user("location")
    } yield {
      (date, text, name, location)
    }
    result

  }
}
