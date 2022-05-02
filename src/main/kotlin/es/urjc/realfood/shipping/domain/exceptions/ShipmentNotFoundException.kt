package es.urjc.realfood.shipping.domain.exceptions

class ShipmentNotFoundException(id: String) : RuntimeException(String.format("Shipment '%s' not found",id))