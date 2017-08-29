package response

import api._
import scala.util.Random
import scala.util.parsing.json._

class GetGitHubDetails extends ResponseHandler[(String) => scala.collection.IndexedSeq[String]] {
  def processResponse(config: (String) => String) = (gitHubResp: String) => {
    val result = for {
      Some(M(map)) <- List(JSON.parseFull(gitHubResp))
      L(githubItems) = map("items")
      M(project) <- githubItems
      S(full_name) = project("full_name")

    } yield {
      (full_name)
    }
    val numOfRequest = config("number_of_request").toInt
    val range = 1 to numOfRequest
    val random = new Random()
    val filtered: IndexedSeq[String] = range.map(x => random.nextInt(result.size)).map(y => result(y))
    filtered
  }
}
