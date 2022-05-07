package es.urjc.realfood.shipping.application.trackshipment

import es.urjc.realfood.shipping.domain.ShipmentRepository
import es.urjc.realfood.shipping.domain.exceptions.ShipmentNotFoundException
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class TrackShipment(private val shipmentRepository: ShipmentRepository) {

    operator fun invoke(request: TrackShipmentRequest): TrackShipmentResponse {
        val shipment = shipmentRepository.getByIdAndClientId(request.shipmentId, request.clientId)
            ?: throw ShipmentNotFoundException(request.shipmentId)
        return TrackShipmentResponse(
            status = shipment.status.toString(),
            shipmentDate = shipment.getCreatedAt().toString(),
            lastUpdateDate = shipment.getLastUpdate().toString()
        )
    }

}


