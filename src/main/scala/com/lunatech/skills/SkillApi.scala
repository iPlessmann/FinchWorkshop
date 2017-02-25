package com.lunatech.skills

import java.util.UUID

import com.lunatech.skills.Routes._
import com.lunatech.skills.models.Skill
import com.twitter.finagle.stats.Counter
import com.twitter.logging.Logger
import io.finch._

/**
  * Created by erikjanssen on 12/02/2017.
  */
trait SkillApi {

  val log: Logger

  val skillsCounter: Counter

  val getskillsParams = q :: skip :: limit

  def postedSkill: Endpoint[Skill] = ???

  def addSkillByUserId: Endpoint[Skill] = ???

  def getSkills: Endpoint[List[Skill]] = ???

  val skillApi = getSkills

}
