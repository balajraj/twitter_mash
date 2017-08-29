package response

import scala.util.parsing.json._
import api._

class ParseAccessTokenResponse extends ResponseHandler[(String) => String] {

  def processResponse(config: (String) => String) = (resp: String) => {
    val jsonR = JSON.parseFull(resp)
    jsonR match {
      // Matches if jsonStr is valid JSON and represents a Map of Strings to Any
      case Some(map: Map[String, String]) => map("access_token")
      case other                          => throw new RuntimeException(s"Unknown data structure: $other")
    }
  }

}
