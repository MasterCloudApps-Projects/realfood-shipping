package es.urjc.realfood.shipping.application.trackshipment

data class TrackShipmentResponse(
    val shipmentDate: String,
    val lastUpdateDate: String,
    val status: String,
)
