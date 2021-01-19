package io.nais

import io.ktor.application.*
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.response.*
import io.ktor.routing.*

@Suppress("unused") // referenced in application.conf
fun Application.observabilityModule() {
   routing {
      get("/internal/isalive") {
         call.respond(OK)
      }

      get("/internal/isready") {
         call.respond(OK)
      }
   }
}

