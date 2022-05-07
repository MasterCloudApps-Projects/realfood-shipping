package es.urjc.realfood.shipping.application.trackshipment

data class TrackShipmentRequest(
    val shipmentId: String,
    val clientId: String,
)
