package driver

import scala.util.{ Success, Failure }
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent._
import com.typesafe.scalalogging._
import java.io.PrintWriter

import config._
import request._

object GitHubTwitterDriver extends LazyLogging {
 
  def sendOutRequests(file: String): Unit = {
    val config = new ConfigFile(file);
    val conf = config.getConfig()
    val httpWrapper = new HttpWrapper()
    val githubInfo = new GetReactiveRepo(httpWrapper)
    val listproj = githubInfo.processRequest(conf)
    val gitprojects = githubInfo.processGitHubResultAndGetAuthToken(listproj,conf)
    val bearer = new GetBearerHandler(gitprojects, httpWrapper)
    val bearerJson = bearer.processRequest(conf)
    val listTweets = bearer.processRespAndCallTwitterSearch(bearerJson, conf)
    processOutput(listTweets,conf)
  }
  
  def processOutput(listTweets:Array[GitTweets],config: (String) => String) : Unit = {
    import org.json4s._
    import org.json4s.JsonDSL._
    import org.json4s.jackson.JsonMethods._

     val jsonstr =
          ("result_tweets" -> 
            listTweets.toList.map { x =>
              ("repo" -> x.projectName) ~
                ("tweets" -> x.tweets.map {
                  y => 
                    ("date" -> y._1) ~
                      ("text" -> y._2) ~
                      ("person" -> y._3) ~
                      ("location" -> y._4)
                })
            })
        val output = compact(render(jsonstr))
        logger.info(output)
        new PrintWriter(config("output_file")) { write(output); close }

  }

  def main(args: Array[String]): Unit = {
    val file = sys.env("config_file")
    if (file.isEmpty()) {
      logger.info("config file env variable config_file not set");
      System.exit(1);
    }
    sendOutRequests(file);
  }

}
