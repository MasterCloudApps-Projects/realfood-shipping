package es.urjc.realfood.shipping.domain

import java.time.Instant
import java.util.*
import javax.persistence.Entity
import javax.persistence.Enumerated
import javax.persistence.Id

@Entity
class Shipment(
    val orderId: String,
    val clientId: String,
) {

    @Id
    val id = UUID.randomUUID().toString()
    private val createdAt = Date()
    private var lastUpdate: Date = Date()
    @Enumerated
    var status: ShipmentStatus = ShipmentStatus.PENDING

    fun updateStatus(status: ShipmentStatus) {
        if(this.status.next != status)
            throw IllegalStateException("Shipment '$id' invalid next status '$status', it must be '${this.status.next}'")
        if(status == this.status)
            return

        this.status = status
        this.lastUpdate = Date()
    }

    fun getCreatedAt(): Instant {
        return createdAt.toInstant()
    }

    fun getLastUpdate(): Instant {
        return lastUpdate.toInstant()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Shipment

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }


}