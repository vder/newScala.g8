package example

import cats._
import cats.data._
import cats.effect.Sync
import cats.implicits._
import io.circe.syntax._
import org.http4s.HttpRoutes
import org.http4s.circe._
import org.http4s.client.dsl.io._
import org.http4s.dsl.Http4sDsl
import org.http4s.dsl.impl.QueryParamDecoderMatcher
import org.http4s.implicits._
import org.http4s.server.Router
import io.chrisdavenport.log4cats.Logger

final class BaseRoutes[F[_]: Defer: Logger: MonadError[*[_],Throwable] ] private {
  
     val httpRoutes: HttpRoutes[F] = {
    val dsl = new Http4sDsl[F] {}
    import dsl._
    HttpRoutes.of {
      case GET -> Root  => Ok("Ok")
    }
  }
 
   private[this] val prefixPath = "/"


  val routes: HttpRoutes[F] = Router(
    prefixPath -> httpRoutes
  )
  
}


object BaseRoutes {
    def make[F[_]: Defer: Logger: MonadError[*[_],Throwable]: Sync] = Sync[F].delay{new BaseRoutes[F]()}
}
