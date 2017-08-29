package api

//import akka.actor.ActorRef

/**
 * All the response handling class will implement this trait
 */
trait ResponseHandler[A] {
  def processResponse(f: (String) => String): A
}

/**
 * All the request handling class will implement this trait
 */
trait RequestHandler[A] {
  def processRequest(config: (String) => String) : A
}

