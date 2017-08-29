
import org.scalatest._
import response._
import config._
import scala.collection.mutable.StringBuilder
import scala.io.Source

class GetGitHubDetailsTest extends FlatSpec with Matchers {

  "The class " should "return indexed sequence of string based on response data" in
    {
      val details = new GetGitHubDetails

      val config = new ConfigFile("src/main/resources/config.properties");
      val bufferedSource = Source.fromFile("src/test/resources/githubdetails.json")
      val builder = new StringBuilder
      for (line <- bufferedSource.getLines) {
        builder.append(line)
      }
      val resp = builder.toString
      val respFunc = details.processResponse(config.getConfig())
      val result = respFunc(resp)
      assert(result.contains("component/reactive") == true)
      assert(result.contains("nafg/reactive") == true)
    }

}