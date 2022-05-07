package es.urjc.realfood.shipping.application.createshipment

import es.urjc.realfood.shipping.domain.Shipment
import es.urjc.realfood.shipping.domain.ShipmentRepository
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class CreateShipment(private val shipmentRepository: ShipmentRepository) {

    operator fun invoke(request: CreateShipmentRequest): CreateShipmentResponse {
        val newShipment = Shipment(
            orderId = request.orderId, clientId = request.clientId
        )
        shipmentRepository.save(newShipment)
        return CreateShipmentResponse(shippingId = newShipment.id, status = newShipment.status.name)
    }

}
