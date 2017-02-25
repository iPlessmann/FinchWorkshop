package com.lunatech

import com.twitter.finagle.http.Status
import io.circe.{Encoder, Json}

/**
  * Created by erikjanssen on 12/02/2017.
  */
package object skills {

  implicit val encodeException: Encoder[Exception] = Encoder.instance(
    e =>
      Json.obj(
        "type"    -> Json.fromString(e.getClass.getSimpleName),
        "message" -> Json.fromString(e.getMessage)
    ))

  implicit val encodeStatus: Encoder[Status] = Encoder.instance(
    s =>
      Json.obj(
        "code"    -> Json.fromInt(s.code),
        "type"    -> Json.fromString(s.getClass.getSimpleName),
        "message" -> Json.fromString(s.reason)
    ))

}
