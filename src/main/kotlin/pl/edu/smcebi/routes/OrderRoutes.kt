package pl.edu.smcebi.routes

import pl.edu.smcebi.models.*
import io.ktor.server.application.*
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.UUID

fun Route.listOrdersRoute() {
    get("/order") {
        if (orderStorage.isNotEmpty()) {
            call.respond(orderStorage)
        }
    }
}

fun Route.createNewOrder() {
    post("/order/new") {
        val orderItems = call.receive<List<OrderItem>>()
        val newOrder = orderItems.toNewOrder()
        orderStorage.add(newOrder)
        call.respondText(
            "Zamówienie o ID ${newOrder.number} zostało pomyślnie złożone",
            status = HttpStatusCode.Created
        )
    }
}

private fun List<OrderItem>.toNewOrder() = Order(
    number = "${getCurrentDate()}-${UUID.randomUUID()}",
    contents = this
)

private fun getCurrentDate(): String = LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_PATTERN))

const val DATE_PATTERN = "yyyy-MM-dd"