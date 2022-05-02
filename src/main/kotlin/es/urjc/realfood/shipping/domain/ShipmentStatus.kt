package es.urjc.realfood.shipping.domain

enum class ShipmentStatus(val next: ShipmentStatus) {
    DELIVERED(DELIVERED),
    IN_ROUTE(DELIVERED),
    PENDING(IN_ROUTE);
}