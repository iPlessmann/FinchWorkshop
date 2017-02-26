package com.lunatech.skills.models

import java.util.UUID

import io.circe.{Decoder, Encoder, Json}
import io.circe.syntax._

/**
  * Created by tatianamoldovan on 05/02/2017.
  */
case class User(id: UUID, firstName: String, lastName: String, email: String) extends Model

object User extends DB[User] {

  implicit val encodeUser: Encoder[User] = new Encoder[User] {
    override def apply(skill: User): Json = Json.obj(
      "id"        -> skill.id.asJson,
      "firstName" -> skill.firstName.asJson,
      "lastName"  -> skill.lastName.asJson,
      "email"     -> skill.email.asJson
    )
  }

  implicit val decodeUser: Decoder[User] = Decoder.instance(c =>
    for {
      id        <- c.downField("id").as[UUID]
      firstName <- c.downField("firstName").as[String]
      lastName  <- c.downField("lastName").as[String]
      email     <- c.downField("skillType").as[String]
    } yield User(id, firstName, lastName, email))

}

case class UserSkills(id: UUID, skills: Set[Skill]) extends Model

object UserSkills extends DB[UserSkills] {

  /*
   * def get(id: UUID): Option[T] = synchronized { db.get(id) }
  def list(): List[T]          = synchronized { db.values.toList }
  def save(t: T): Unit         = synchronized { db += (t.id -> t) }
  def delete(id: UUID): Unit   = synchronized { db -= id }

   */

  override def get(id: UUID): Option[UserSkills] = super.get(id)

  override def list(): List[UserSkills] = super.list()

  override def save(t: UserSkills): Unit = super.save(t)

  override def delete(id: UUID): Unit = super.delete(id)

}

case class UserNotFound(id: UUID) extends Exception {
  override def getMessage: String = s"User(${id.toString}) not found."
}
