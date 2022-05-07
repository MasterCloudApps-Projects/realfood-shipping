package es.urjc.realfood.shipping.infrastructure.data

import es.urjc.realfood.shipping.domain.Shipment
import es.urjc.realfood.shipping.domain.ShipmentStatus
import org.springframework.data.repository.CrudRepository

interface JpaShipmentRepository : CrudRepository<Shipment, String> {
    fun findByStatus(status: ShipmentStatus) : List<Shipment>
    fun findByIdAndClientId(shippingId: String, clientId: String): Shipment?
}