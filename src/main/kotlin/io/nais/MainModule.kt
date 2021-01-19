package io.nais

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

@Suppress("unused") // referenced in application.conf
fun Application.mainModule() {

   routing {
      get("/") {
         call.respond("Hello!")
      }
   }
}

