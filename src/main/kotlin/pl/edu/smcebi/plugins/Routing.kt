package pl.edu.smcebi.plugins

import io.ktor.server.application.*
import io.ktor.server.plugins.autohead.*
import io.ktor.server.routing.*
import pl.edu.smcebi.routes.*

fun Application.configureRouting() {
    install(AutoHeadResponse)

    routing {
        listOrdersRoute()
        createNewOrder()
        getOrderRoute()
        totalizeOrderRoute()
        customerRouting()
    }
}