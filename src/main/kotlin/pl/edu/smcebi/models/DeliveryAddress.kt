package pl.edu.smcebi.models

import kotlinx.serialization.Serializable
/**
 * klasa modelowa przechowująca adres klienta
 *
 * @param city: miasto klienta
 * @param street: ulica klienta
 * @param streetNo: numer ulicy
 * @param postalCode: kod pocztowy
 * @param phoneNumber: numer telefonu
 */
@Serializable
data class DeliveryAddress(
    val city: String?,
    val street: String?,
    val streetNo: String?,
    val postalCode: String?,
    val phoneNumber: String?
)
