package es.urjc.realfood.shipping.infrastructure.messaging

import com.fasterxml.jackson.databind.ObjectMapper
import es.urjc.realfood.shipping.application.createshipment.CreateShipment
import es.urjc.realfood.shipping.application.createshipment.CreateShipmentRequest
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class RabbitMqShipmentRequestConsumer(
    private val createShipment: CreateShipment,
) {

    private val objectMapper = ObjectMapper()
        .findAndRegisterModules()

    @RabbitListener(queues = ["send-order"], ackMode = "AUTO")
    private fun consume(message: String) {
        val shipmentRequestEvent = objectMapper.readValue(message, ShipmentRequestEvent::class.java)

        LOGGER.info(
            "Shipment request from client '{}' and order '{}'",
            shipmentRequestEvent.clientId,
            shipmentRequestEvent.orderId
        )

        createShipment(
            CreateShipmentRequest(
                orderId = shipmentRequestEvent.orderId, clientId = shipmentRequestEvent.clientId
            )
        )
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(RabbitMqShipmentRequestConsumer::class.java)
    }
}

data class ShipmentRequestEvent(
    val clientId: String,
    val orderId: String,
)
