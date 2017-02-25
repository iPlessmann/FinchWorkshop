package com.lunatech.skills.models

import io.circe.Decoder.Result
import io.circe.{Decoder, Encoder, HCursor, Json}
import io.circe.syntax._

/**
  * Created by tatianamoldovan on 03/02/2017.
  */
object EnumTypes {

  sealed trait SkillLevel
  case object CAN_TEACH   extends SkillLevel
  case object COMFORTABLE extends SkillLevel
  case object DABBED      extends SkillLevel
  case object FOSSIL      extends SkillLevel
  case object UNKNOWN     extends SkillLevel

  def skillLevelToString(skillLevel: SkillLevel): String = skillLevel match {
    case CAN_TEACH   => "CAN_TEACH"
    case COMFORTABLE => "COMFORTABLE"
    case DABBED      => "DABBED"
    case FOSSIL      => "FOSSIL"
    case _           => "UNKNOWN"
  }

  def stringToSkillLevel(skillLevel: String): SkillLevel = skillLevel match {
    case "CAN_TEACH"   => CAN_TEACH
    case "COMFORTABLE" => COMFORTABLE
    case "DABBED"      => DABBED
    case "FOSSIL"      => FOSSIL
    case _             => UNKNOWN
  }

  object SkillType extends Enumeration {
    type SkillType = Value
    val LANGUAGE   = Value("LANGUAGE")
    val LIBRARY    = Value("LIBRARY")
    val FRAMEWORK  = Value("FRAMEWORK")
    val CONCEPTUAL = Value("CONCEPTUAL")

    implicit val encodeSkillType: Encoder[SkillType] = new Encoder[SkillType] {
      override def apply(skillType: SkillType): Json = Json.obj(
        "skillType" -> skillType.toString.asJson
      )
    }

    implicit val decodeSkillType = new Decoder[SkillType] {
      override def apply(c: HCursor): Result[SkillType] = c.as[String].map(SkillType.withName)
    }

  }
}
