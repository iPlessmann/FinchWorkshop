package com.lunatech.skills

import java.util.UUID

import com.lunatech.skills.models.{User, UserNotFound}
import com.twitter.finagle.stats.Counter
import com.twitter.logging.Logger
import io.circe.generic.auto._
import io.finch._
import io.finch.circe._
import Routes._

/**
  * Created by erikjanssen on 12/02/2017.
  */
trait UserApi {

  val log: Logger

  val usersCounter: Counter

  def getUser: Endpoint[User] =
    get(users :: uuid) { id: UUID =>
      log.info("serving get user request")
      User.get(id) match {
        case Some(u) => Ok(u)
        case None    => NotFound(UserNotFound(id))
      }
    }

  def postedUser: Endpoint[User] = body.as[UUID => User].map(_(UUID.randomUUID()))

  def postUser: Endpoint[User] = post(users :: postedUser) { u: User =>
    {
      usersCounter.incr()
      User.save(u)
      Created(u)
    }
  }

  def patchedUser: Endpoint[User => User] = body.as[User => User]

  def patchUser: Endpoint[User] =
    patch(users :: uuid :: patchedUser) { (id: UUID, pt: User => User) =>
      User.get(id) match {
        case Some(currentUser) =>
          val newUser: User = pt(currentUser)
          User.delete(id)
          User.save(newUser)

          Ok(newUser)
        case None => throw UserNotFound(id)
      }
    }

  def getUsers: Endpoint[List[User]] = get(users) {
    Ok(User.list())
  }

  def deleteUser: Endpoint[User] = delete(users :: uuid) { id: UUID =>
    User.get(id) match {
      case Some(t) => User.delete(id); Ok(t)
      case None    => throw UserNotFound(id)
    }
  }

  def deleteUsers: Endpoint[List[User]] = delete(users) {
    val all: List[User] = User.list()
    all.foreach(t => User.delete(t.id))

    Ok(all)
  }

  val userApi = getUser :+: getUsers :+: postUser :+: deleteUser :+: deleteUsers :+: patchUser

}
