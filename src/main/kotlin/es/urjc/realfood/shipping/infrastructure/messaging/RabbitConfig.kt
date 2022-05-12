package es.urjc.realfood.shipping.infrastructure.messaging

import org.springframework.amqp.core.AmqpAdmin
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.ExchangeBuilder
import org.springframework.amqp.core.Queue
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class RabbitConfig(private val amqpAdmin: AmqpAdmin) {

    @PostConstruct
    fun setup() {
        amqpAdmin.declareExchange(
            ExchangeBuilder
                .fanoutExchange("shipping-exchange")
                .durable(true)
                .build()
        )
    }
}