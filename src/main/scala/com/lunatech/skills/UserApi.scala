package com.lunatech.skills

import java.util.UUID

import com.lunatech.skills.Routes._
import com.lunatech.skills.models.{User, UserNotFound}
import com.twitter.finagle.stats.Counter
import com.twitter.logging.Logger
import io.finch._

/**
  * Created by erikjanssen on 12/02/2017.
  */
trait UserApi {

  val log: Logger

  val usersCounter: Counter

  def getUser: Endpoint[User] = ???

  def postedUser: Endpoint[User] = ???

  def postUser: Endpoint[User] = ???

  def patchedUser: Endpoint[User => User] = ???

  def patchUser: Endpoint[User] = ???

  def getUsers: Endpoint[List[User]] = ???

  def deleteUser: Endpoint[User] = ???

  def deleteUsers: Endpoint[List[User]] = ???

  val userApi = getUser :+: getUsers :+: postUser :+: deleteUser :+: deleteUsers :+: patchUser

}
