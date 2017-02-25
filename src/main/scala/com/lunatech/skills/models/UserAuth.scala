package com.lunatech.skills.models

import java.util.UUID

/**
  * Created by tatianamoldovan on 05/02/2017.
  */
case class UserAuth(id: UUID, userId: Int, key: String, secret: String)

object UserAuth {}
