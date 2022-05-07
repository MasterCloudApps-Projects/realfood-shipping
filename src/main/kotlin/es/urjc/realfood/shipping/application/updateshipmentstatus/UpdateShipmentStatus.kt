package es.urjc.realfood.shipping.application.updateshipmentstatus

import es.urjc.realfood.shipping.domain.ShipmentRepository
import es.urjc.realfood.shipping.domain.ShipmentStatus
import es.urjc.realfood.shipping.domain.event.EventPublisher
import es.urjc.realfood.shipping.domain.event.ShipmentStatusUpdated
import es.urjc.realfood.shipping.domain.exceptions.ShipmentNotFoundException
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
@Transactional
class UpdateShipmentStatus(
    private val shipmentRepository: ShipmentRepository,
    private val eventPublisher: EventPublisher,
) {

    operator fun invoke(request: UpdateShipmentStatusRequest): UpdateShipmentStatusResponse {
        val shipment = shipmentRepository.getByIdAndClientId(request.shipmentId, request.clientId)
            ?: throw ShipmentNotFoundException(request.shipmentId)
        val shipmentStatus = ShipmentStatus.valueOf(request.newStatus.uppercase(Locale.getDefault()))
        shipment.updateStatus(shipmentStatus)
        eventPublisher.publish(
            ShipmentStatusUpdated(
                clientId = shipment.clientId,
                orderId = shipment.orderId,
                status = shipment.status.toString()
            )
        )
        return UpdateShipmentStatusResponse(
            newStatus = shipment.status.toString(),
        )
    }

}


