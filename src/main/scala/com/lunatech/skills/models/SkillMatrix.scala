package com.lunatech.skills.models

import com.lunatech.skills.models.EnumTypes._
import io.circe.{Decoder, Encoder, Json}
import io.circe.syntax._

/**
  * Created by tatianamoldovan on 05/02/2017.
  */
case class SkillMatrix(id: Int, userId: Int, skillId: Int, skillLevel: SkillLevel)

object SkillMatrix {

  implicit val encodeSkillMatrix: Encoder[SkillMatrix] = new Encoder[SkillMatrix] {
    override def apply(skillMatrix: SkillMatrix): Json = Json.obj(
      "id"         -> skillMatrix.id.asJson,
      "userId"     -> skillMatrix.userId.asJson,
      "skillId"    -> skillMatrix.skillId.asJson,
      "skillLevel" -> skillLevelToString(skillMatrix.skillLevel).asJson
    )
  }

  implicit val decodeSkillMatrix: Decoder[SkillMatrix] = Decoder.instance(c =>
    for {
      id         <- c.downField("id").as[Int]
      userId     <- c.downField("userId").as[Int]
      skillId    <- c.downField("skillId").as[Int]
      skillLevel <- c.downField("skillLevel").as[String] map stringToSkillLevel
    } yield SkillMatrix(id, userId, skillId, skillLevel))

}
