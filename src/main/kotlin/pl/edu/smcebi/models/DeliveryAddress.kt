package pl.edu.smcebi.models

import kotlinx.serialization.Serializable

@Serializable
data class DeliveryAddress(
    val city: String?,
    val street: String?,
    val streetNo: String?,
    val postalCode: String?,
    val phoneNumber: String?
)
