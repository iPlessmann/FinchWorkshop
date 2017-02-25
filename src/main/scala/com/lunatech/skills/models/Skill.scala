package com.lunatech.skills.models

import java.util.UUID

import com.lunatech.skills.models.EnumTypes.SkillType.SkillType
import io.circe.syntax._
import io.circe.{Decoder, Encoder, Json}

case class Skill(name: String, skillType: SkillType) extends Model {
  override def id: UUID = UUID.randomUUID()
}

object Skill extends DB[Skill] {

  implicit val encodeSkill: Encoder[Skill] = new Encoder[Skill] {
    override def apply(skill: Skill): Json = Json.obj(
      "name"      -> skill.name.asJson,
      "skillType" -> skill.skillType.asJson
    )
  }

  implicit val decodeSkill: Decoder[Skill] = Decoder.instance(c =>
    for {
      name      <- c.downField("name").as[String]
      skillType <- c.downField("skillType").as[SkillType]
    } yield Skill(name, skillType))

}
