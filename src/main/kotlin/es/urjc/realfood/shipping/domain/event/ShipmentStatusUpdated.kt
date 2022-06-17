package es.urjc.realfood.shipping.domain.event

data class ShipmentStatusUpdated(
    val clientId: String,
    val orderId: String,
    val status: String,
) : DomainEvent
