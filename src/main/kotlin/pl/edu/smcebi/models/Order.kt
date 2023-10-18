package pl.edu.smcebi.models

import kotlinx.serialization.Serializable

/**
 * Klasa modelowa wykorzystywana do stworzenia zamówienia
 *
 * @param number: ID zamówienia
 * @param contents: Lista elementów składających się na dane zamówienie
 */
@Serializable
data class Order(
    val number: String,
    val contents: List<OrderItem>
)

/**
 * Klasa modelowa służąca do przechowywania informacji o elemencie danego zamówienia
 *
 * @param item: Nazwa produktu
 * @param amount: Ilość zamówionych produktów
 * @param price: Cena odnosząca się do jednej sztuki produktu
 */
@Serializable
data class OrderItem(
    val item: String,
    val amount: Int,
    val price: Double
)

val orderStorage = mutableListOf(
    Order(
        "2023-01-15-01", listOf(
            OrderItem("Kanapka z szynką", 2, 5.50),
            OrderItem("Woda", 1, 1.50),
            OrderItem("Piwo", 3, 2.30),
            OrderItem("Sernik", 1, 3.75)
        )
    ),
    Order(
        "2023-01-15-02", listOf(
            OrderItem("Cheeseburger", 1, 8.50),
            OrderItem("Woda", 2, 1.50),
            OrderItem("Cola", 2, 1.76),
            OrderItem("Lody karpatkowe", 1, 2.35)
        )
    )
)