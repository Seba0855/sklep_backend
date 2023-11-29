package pl.edu.smcebi.plugins

import io.ktor.server.application.*
import io.ktor.server.plugins.autohead.*
import io.ktor.server.routing.*
import pl.edu.smcebi.routes.*
import io.ktor.server.plugins.swagger.*

fun Application.configureRouting() {
    install(AutoHeadResponse)

    routing {
        swaggerUI(path = "swagger", swaggerFile = "openapi/documentation.yaml")

        orderRouting()
        customerRouting()
    }
}