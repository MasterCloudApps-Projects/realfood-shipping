package es.urjc.realfood.shipping.application.updateshipmentstatus

data class UpdateShipmentStatusRequest(
    val shipmentId: String,
    val clientId: String,
    val newStatus: String,
)
