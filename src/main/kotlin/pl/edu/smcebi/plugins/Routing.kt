package pl.edu.smcebi.plugins

import io.ktor.server.application.*
import io.ktor.server.plugins.autohead.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    install(AutoHeadResponse)

    routing {
        listOrdersRoute()
        createNewOrder()
    }
}