package pl.edu.smcebi.extensions

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*

suspend fun ApplicationCall.respondWithBadRequest(): Unit =
    respondText("Bad Request", status = HttpStatusCode.BadRequest)
