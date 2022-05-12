package es.urjc.realfood.shipping.infrastructure.messaging

import com.fasterxml.jackson.databind.ObjectMapper
import es.urjc.realfood.shipping.domain.event.DomainEvent
import es.urjc.realfood.shipping.domain.event.EventPublisher
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component

@Component
class RabbitMqShipmentEventPublisher(
    private val rabbitTemplate: RabbitTemplate
) : EventPublisher {

    private val objectMapper = ObjectMapper()
        .findAndRegisterModules()

    private val exchange: String = "shipping-exchange"

    override fun publish(event: DomainEvent) {
        val msg: String = objectMapper.writeValueAsString(event)
        rabbitTemplate.convertAndSend(exchange, event::class.java.simpleName, msg)
        LOGGER.info("[Publisher] Event sent to exchange '{}': {}", exchange, msg)
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(RabbitMqShipmentRequestConsumer::class.java)
    }
}