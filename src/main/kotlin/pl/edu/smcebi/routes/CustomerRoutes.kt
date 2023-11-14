package pl.edu.smcebi.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import pl.edu.smcebi.models.Customer
import pl.edu.smcebi.models.DeliveryAddress
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
        get("{id?}") {
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest
            )
            val customer =
                customerStorage.find { it.id == id } ?: return@get call.respondText(
                    "Brak klienta o ID $id",
                    status = HttpStatusCode.NotFound
                )
            call.respond(customer)
        }
        post {
            val customer = call.receive<Customer>()
            customerStorage.add(customer)
            call.respondText("Klient został dodany pomyślnie", status = HttpStatusCode.Created)
        }
        delete("{id?}") {
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
            if (customerStorage.removeIf { it.id == id }) {
                call.respondText("Klient został pomyślnie usunięty z bazy", status = HttpStatusCode.Accepted)
            } else {
                call.respondText("Not Found", status = HttpStatusCode.NotFound)
            }
        }
        post("/{id}/address") {
            val customerId = call.parameters["id"]

            if (customerId != null) {

                val newAddress = call.receive<DeliveryAddress>()
                val customer = customerStorage.find { it.id == customerId }

                if (customer != null) {
                    customer.deliveryAddress = newAddress
                    call.respond(HttpStatusCode.OK, "Adres zaktualizowany pomyślnie dla $customerId")
                } else {
                    call.respond(HttpStatusCode.NotFound, "Klient nie znaleziony")
                }
            } else {
                call.respond(HttpStatusCode.BadRequest, "Zły ID klienta")
            }
        }
        delete("/{id}/address"){
            val customerId = call.parameters["id"]
            if (customerId != null) {
                val customer = customerStorage.find { it.id == customerId }

                if (customer != null) {
                    customer.deliveryAddress = null
                    call.respond(HttpStatusCode.OK, "Address removed for customer $customerId")
                } else {
                    call.respond(HttpStatusCode.NotFound, "Customer not found")
                }
            } else {
                call.respond(HttpStatusCode.BadRequest, "Invalid customer ID")
            }
        }
    }
}