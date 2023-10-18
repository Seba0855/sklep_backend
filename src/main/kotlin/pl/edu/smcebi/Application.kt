package pl.edu.smcebi

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.cors.routing.*
import pl.edu.smcebi.plugins.configureRouting
import pl.edu.smcebi.plugins.configureSecurity
import pl.edu.smcebi.plugins.configureSerialization

fun main() {
    embeddedServer(Netty, port = 2137, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    install(CORS) {
        anyHost()
        allowHeader(HttpHeaders.ContentType)
    }
    configureSerialization()
    configureSecurity()
    configureRouting()
}
