package es.urjc.realfood.shipping.domain.event

import java.time.Instant
import java.util.*

abstract class DomainEvent {
    val id = UUID.randomUUID().toString()
    val createdAt = Instant.now().toEpochMilli()
}