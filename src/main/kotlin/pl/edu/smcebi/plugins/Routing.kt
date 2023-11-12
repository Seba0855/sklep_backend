package pl.edu.smcebi.plugins

import io.ktor.server.application.*
import io.ktor.server.plugins.autohead.*
import io.ktor.server.routing.*
import pl.edu.smcebi.routes.createNewOrder
import pl.edu.smcebi.routes.getOrderRoute
import pl.edu.smcebi.routes.listOrdersRoute
import pl.edu.smcebi.routes.totalizeOrderRoute

fun Application.configureRouting() {
    install(AutoHeadResponse)

    routing {
        listOrdersRoute()
        createNewOrder()
        getOrderRoute()
        totalizeOrderRoute()
    }
}