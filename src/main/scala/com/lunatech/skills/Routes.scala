package com.lunatech.skills

import io.finch._

/**
  *
  * Holds common names in paths like parameters and static paths
  *
  * */
object Routes {

  val skills: Endpoint0 = "skills"
  val matrix: Endpoint0 = "matrix"
  val users: Endpoint0  = "users"

  val uid: Endpoint[Option[String]]   = paramOption("userId")
  val q: Endpoint[Option[String]]     = paramOption("searchString")
  val skip: Endpoint[Option[String]]  = paramOption("skip")
  val limit: Endpoint[Option[String]] = paramOption("limit")

}
