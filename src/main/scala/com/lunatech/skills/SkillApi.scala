package com.lunatech.skills

import java.util.UUID

import com.lunatech.skills.Routes._
import com.lunatech.skills.models.Skill
import com.twitter.finagle.stats.Counter
import com.twitter.logging.Logger
import io.circe.generic.auto._
import io.finch._
import io.finch.circe._

/**
  * Created by erikjanssen on 12/02/2017.
  */
trait SkillApi {

  val log: Logger

  val skillsCounter: Counter

  val getskillsParams = q :: skip :: limit

  def postedSkill: Endpoint[Skill] = body.as[Skill]

  def addSkillByUserId: Endpoint[Skill] = post(users :: uuid :: skills :: postedSkill) {
    (uid: UUID, s: Skill) =>
      {
        skillsCounter.incr()
        Skill.save(s)
        Created(s)
      }
  }

  def getSkills: Endpoint[List[Skill]] =
    get(skills :: getskillsParams) {
      (q: Option[String], skip: Option[String], limit: Option[String]) =>
        {
          log.debug("get skill request")
          val skills = Skill.list()
          val result = (q, skip, limit) match {
            case (Some(q), Some(s), Some(l)) =>
              skills.filter(s => s.name.contains(q)).slice(s.toInt, l.toInt + s.toInt)
            case (Some(q), _, _)       => skills.filter(s => s.name.contains(q))
            case (_, Some(s), Some(l)) => skills.slice(s.toInt, l.toInt + s.toInt)
            case (_, _, _)             => skills
          }
          Ok(result)
        }
    }

  val skillApi = getSkills :+: addSkillByUserId

}
