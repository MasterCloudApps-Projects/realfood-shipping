package es.urjc.realfood.shipping.infrastructure.data

import es.urjc.realfood.shipping.domain.Shipment
import es.urjc.realfood.shipping.domain.ShipmentRepository
import es.urjc.realfood.shipping.domain.ShipmentStatus
import org.springframework.stereotype.Service

@Service
class PostgresShipmentRepository(
    private val jpaShipmentRepository: JpaShipmentRepository,
) : ShipmentRepository {
    override fun save(shipment: Shipment) {
        jpaShipmentRepository.save(shipment)
    }

    override fun getByStatus(status: ShipmentStatus): List<Shipment> {
        return jpaShipmentRepository.findByStatus(status)
    }

    override fun getByIdAndClientId(shippingId: String, clientId: String): Shipment? {
        return jpaShipmentRepository.findByIdAndClientId(shippingId, clientId)
    }
}