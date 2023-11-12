package pl.edu.smcebi.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import pl.edu.smcebi.extensions.getCurrentDate
import pl.edu.smcebi.extensions.respondWithBadRequest
import pl.edu.smcebi.models.Order
import pl.edu.smcebi.models.OrderItem
import pl.edu.smcebi.models.orderStorage
import java.util.*

fun Route.listOrdersRoute() {
    get("/order") {
        if (orderStorage.isNotEmpty()) {
            call.respond(orderStorage)
        }
    }
}

fun Route.getOrderRoute() {
    get("/order/{id?}") {
        val id = call.parameters["id"] ?: return@get call.respondWithBadRequest()
        val order = orderStorage.find { it.number == id } ?: return@get call.respondText(
            "Nie znaleziono zamówienia o podanym ID",
            status = HttpStatusCode.NotFound
        )
        call.respond(order)
    }
}

fun Route.totalizeOrderRoute() {
    get("/order/{id?}/total") {
        val id = call.parameters["id"] ?: return@get call.respondWithBadRequest()
        val order = orderStorage.find { it.number == id } ?: return@get call.respondText(
            "Nie znaleziono zamówienia o podanym ID",
            status = HttpStatusCode.NotFound
        )
        val total = order.contents.sumOf { it.price * it.amount }
        call.respond(total)
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