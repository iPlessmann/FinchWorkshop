package com.lunatech.skills.models

import java.util.UUID

/**
  * Created by tatianamoldovan on 05/02/2017.
  */
case class User(id: UUID, firstName: String, lastName: String, email: String) extends Model

object User extends DB[User] {}

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
