package pl.edu.smcebi.plugins

import io.ktor.server.application.*
import io.ktor.server.plugins.autohead.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    install(AutoHeadResponse)

    routing {
        // our endpoint routes should go there
    }
}