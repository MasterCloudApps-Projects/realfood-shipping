package es.urjc.realfood.shipping.application.createshipment

data class CreateShipmentRequest(
    val orderId: String,
    val clientId: String,
)
