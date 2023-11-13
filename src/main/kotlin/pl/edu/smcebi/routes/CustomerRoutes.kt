package pl.edu.smcebi.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import pl.edu.smcebi.models.Customer
import pl.edu.smcebi.models.customerStorage

fun Route.customerRouting() {
    route("/customer") {

        get {
            if (customerStorage.isNotEmpty()) {
                call.respond(customerStorage)
            } else {
                call.respondText("Brak klientów w bazie", status = HttpStatusCode.OK)
            }
        }

        post {
            val customer = call.receive<Customer>()
            customerStorage.add(customer)
            call.respondText("Klient został dodany pomyślnie", status = HttpStatusCode.Created)
        }
    }
}