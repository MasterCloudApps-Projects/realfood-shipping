package es.urjc.realfood.shipping.domain

interface ShipmentRepository {
    fun save(shipment: Shipment)
    fun getByStatus(status: ShipmentStatus) : List<Shipment>
    fun getByIdAndClientId(shippingId: String, clientId: String): Shipment?
}