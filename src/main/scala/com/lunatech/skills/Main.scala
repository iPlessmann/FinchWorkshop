package com.lunatech.skills

import com.lunatech.skills.models.UserNotFound
import com.twitter.app.Flag
import com.twitter.finagle.http.{Request, Response}
import com.twitter.finagle.stats.Counter
import com.twitter.finagle.{Http, Service}
import com.twitter.server.TwitterServer
import com.twitter.util.Await
import io.circe.generic.auto._
import io.finch._
import io.finch.circe._

/**
  * A simple Finch application implementing the backend for the TodoMVC project.
  *
  * Use the following sbt command to run the application.
  *
  *
  * Use the following HTTPie commands to test endpoints.
  *
  * {{{
  *   $ http POST :8081/users firstName=John lastName=Doe email=info@bizz.com
  *   $ http PATCH :8081/users/<UUID> completed:=true
  *   $ http :8081/users
  *   $ http DELETE :8081/users/<UUID>
  *   $ http DELETE :8081/users
  * }}}
  */
object Main extends TwitterServer with UserApi {

  val port: Flag[Int] = flag("port", 8081, "TCP port for HTTP server")

  val usersCounter: Counter = statsReceiver.counter("users")

  val api: Service[Request, Response] = userApi
    .handle({
      case e: UserNotFound => NotFound(e)
    })
    .toServiceAs[Application.Json]

  def main(): Unit = {
    log.info("Serving the Skills application")

    val server = Http.server.withStatsReceiver(statsReceiver).serve(s":${port()}", api)

    onExit { server.close() }

    Await.ready(adminHttpServer)
  }
}
