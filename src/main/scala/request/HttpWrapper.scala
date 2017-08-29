package request
import dispatch._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
import scala.concurrent.duration._

/**
 * The class exists due to the reason it makes mocking 
 * http request easier in the unit test cases. 
 */
class HttpWrapper {
  
  def sendRequest(finalReq: Req,count:Int): String = {
    try {
      val resp = Http(finalReq OK as.String)
      Await.result(resp,5.seconds)       
    }
    catch {
      case e:Exception => {
        if ( count == 0 ) 
        {
          sendRequest(finalReq,1) 
        }
        else {
          e.printStackTrace
          ""
        }        
      }
    }
  }
}