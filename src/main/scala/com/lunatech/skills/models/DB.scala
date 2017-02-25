package com.lunatech.skills.models

import java.util.UUID

import scala.collection.mutable

/**
  * Created by erikjanssen on 11/02/2017.
  */
trait Model {
  def id: UUID
}

trait DB[T <: Model] {
  private[this] val db: mutable.Map[UUID, T] = mutable.Map.empty[UUID, T]

  def get(id: UUID): Option[T] = synchronized { db.get(id) }
  def list(): List[T]          = synchronized { db.values.toList }
  def save(t: T): Unit         = synchronized { db += (t.id -> t) }
  def delete(id: UUID): Unit   = synchronized { db -= id }

}
