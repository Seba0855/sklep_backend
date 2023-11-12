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
import pl.edu.smcebi.models.OrderStatus
import pl.edu.smcebi.models.orderStorage
import java.util.*

/**
 * Add here new routes
 */
fun Route.orderRouting() {
    listOrdersRoute()
    orderIdRoute()
    orderStatusRoute()
    totalizeOrderRoute()
    createNewOrder()
}

private fun Route.listOrdersRoute() {
    get("/order") {
        if (orderStorage.isNotEmpty()) {
            call.respond(orderStorage)
        }
    }
}

private fun Route.orderIdRoute() {
    route("/order/{id?}") {
        get {
            val id = call.parameters["id"] ?: return@get call.respondWithBadRequest()
            val order = orderStorage.find { it.number == id } ?: return@get call.respondText(
                "Nie znaleziono zamówienia o podanym ID",
                status = HttpStatusCode.NotFound
            )
            call.respond(order)
        }
    }
}

private fun Route.orderStatusRoute() {
    put("/order/{id?}/status") {
        val id = call.parameters["id"] ?: return@put call.respondWithBadRequest()
        val newStatus = call.receive<OrderStatus>()
        val order = orderStorage.find { it.number == id } ?: return@put call.respondText(
            "Nie znaleziono zamówienia o podanym ID",
            status = HttpStatusCode.NotFound
        )
        val updatedOrder = order.copy(status = newStatus)
        orderStorage.apply {
            val orderIndex = indexOf(order)
            removeAt(orderIndex)
            add(orderIndex, updatedOrder)
        }

        call.respond(updatedOrder)
    }
}

private fun Route.totalizeOrderRoute() {
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

private fun Route.createNewOrder() {
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