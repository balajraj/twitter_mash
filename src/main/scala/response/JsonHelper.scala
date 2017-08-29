

package response

/**
 * This helper class is mainly used for decoding the json response using
 * plain old scala method for json processing. 
 */
class CC[T] { def unapply(a: Any): Option[T] = Some(a.asInstanceOf[T]) }

object M extends CC[Map[String, Any]]
object L extends CC[List[Any]]
object S extends CC[String]