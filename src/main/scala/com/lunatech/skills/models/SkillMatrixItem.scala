package com.lunatech.skills.models

import com.lunatech.skills.models.EnumTypes._
import io.circe.{Decoder, Encoder, Json}
import io.circe.syntax._

/**
  * Created by tatianamoldovan on 10/02/2017.
  */
case class SkillMatrixItem(skill: Skill, skillLevel: SkillLevel)

object SkillMatrixItem {

  implicit val encodeSkillMatrixItem: Encoder[SkillMatrixItem] = new Encoder[SkillMatrixItem] {
    override def apply(skillMatrixItem: SkillMatrixItem): Json = Json.obj(
      "skill"      -> skillMatrixItem.skill.asJson,
      "skillLevel" -> skillLevelToString(skillMatrixItem.skillLevel).asJson
    )
  }

  implicit val decodeSkillMatrixItem: Decoder[SkillMatrixItem] = Decoder.instance(c =>
    for {
      skill      <- c.downField("skill").as[Skill]
      skillLevel <- c.downField("skillLevel").as[String] map stringToSkillLevel
    } yield SkillMatrixItem(skill, skillLevel))

}
