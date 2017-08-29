package response

import api._
import scala.util.parsing.json._

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
