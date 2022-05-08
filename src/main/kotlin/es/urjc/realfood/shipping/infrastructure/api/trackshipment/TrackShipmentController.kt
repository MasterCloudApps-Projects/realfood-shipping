package es.urjc.realfood.shipping.infrastructure.api.trackshipment

import es.urjc.realfood.shipping.application.trackshipment.TrackShipment
import es.urjc.realfood.shipping.application.trackshipment.TrackShipmentRequest
import es.urjc.realfood.shipping.application.trackshipment.TrackShipmentResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
class TrackShipmentController(private val trackShipment: TrackShipment) {

    @GetMapping(ENDPOINT_PATH)
    fun getTrackShipment(
        @PathVariable shipmentId: String,
        principal: Principal,
    ): TrackShipmentResponse =
        trackShipment(
            TrackShipmentRequest(
                shipmentId = shipmentId,
                clientId = principal.name
            )
        )

    companion object {
        const val ENDPOINT_PATH = "/api/shipments/{shipmentId}"
    }
}