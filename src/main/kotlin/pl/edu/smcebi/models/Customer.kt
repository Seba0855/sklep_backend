package pl.edu.smcebi.models

import kotlinx.serialization.Serializable
import java.util.UUID

/**
 * Klasa modelowa przechowująca dane o kliencie
 *
 * @param id: ID klienta
 * @param firstName: Imię klienta
 * @param lastName: Nazwisko klienta
 * @param email: Adres email klienta
 * @param deliveryAddress: adres klienta
 */
@Serializable
data class Customer(
    val id: String = UUID.randomUUID().toString(),
    val firstName: String,
    val lastName: String,
    val email: String,
    var deliveryAddress: DeliveryAddress? = null
)

val customerStorage = mutableListOf<Customer>()