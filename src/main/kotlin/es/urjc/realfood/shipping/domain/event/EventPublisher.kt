package es.urjc.realfood.shipping.domain.event

interface EventPublisher {
    fun publish(event: DomainEvent)
}