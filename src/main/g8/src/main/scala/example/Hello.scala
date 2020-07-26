package example


import cats._
import cats.data._
import cats.effect._
import cats.implicits._
import doobie.hikari._
import doobie.util.ExecutionContexts
import io.chrisdavenport.log4cats.Logger
import io.chrisdavenport.log4cats.slf4j.Slf4jLogger
import io.circe.Json
import io.circe.syntax._
import org.http4s.Method._
import org.http4s._
import org.http4s.circe._
import org.http4s.client.blaze.BlazeClientBuilder
import org.http4s.client.dsl.io._
import org.http4s.client.middleware.FollowRedirect
import org.http4s.headers._
import org.http4s.implicits._
import org.http4s.server.blaze.BlazeServerBuilder
import scala.concurrent.ExecutionContext.global

object Hello extends IOApp {

  type ErrorOr[F[_]] = ApplicativeError[F, Throwable]

  implicit def unsafeLogger[F[_]: Sync] = Slf4jLogger.getLogger[F]

  val resources  =
    for {
      ce <- ExecutionContexts.fixedThreadPool[IO](32) // our connect EC
      be <- Blocker[IO]    // our blocking EC
      xa <- HikariTransactor.newHikariTransactor[IO](
              "org.postgresql.Driver",                        // driver classname
              "jdbc:postgresql://localhost:54340/exchange",   // connect URL
              "vder",                                   // username
              "gordon",                                     // password
              ce,                                     // await connection here
              be                                      // execute JDBC operations here
            )
      client <- BlazeClientBuilder[IO](global).resource      
    } yield (xa, client)



  override def run(args: List[String]): IO[ExitCode] =
    resources.use {
      case (_,client) =>
        for {
          routes <- BaseRoutes.make[IO]
          httpApp = routes.routes.orNotFound
          _ <- BlazeServerBuilder[IO](global)
            .bindHttp(
              9090,
              "localhost"
            )
            .withHttpApp(httpApp)
            .serve
            .compile
            .drain
        } yield ExitCode.Success

    }

}
