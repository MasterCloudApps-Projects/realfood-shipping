package es.urjc.realfood.shipping.infrastructure.batch

import es.urjc.realfood.shipping.application.updateshipmentstatus.UpdateShipmentStatus
import es.urjc.realfood.shipping.application.updateshipmentstatus.UpdateShipmentStatusRequest
import es.urjc.realfood.shipping.domain.ShipmentRepository
import es.urjc.realfood.shipping.domain.ShipmentStatus
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class ShipmentUpdateBatch(
    private val shipmentRepository: ShipmentRepository,
    private val updateShipmentStatus: UpdateShipmentStatus,
) {

    @Scheduled(cron = "\${batch.shipments.rate}")
    operator fun invoke() {
        LOGGER.info("Running ShipmentUpdateBatch")
        listOf(ShipmentStatus.PENDING, ShipmentStatus.IN_ROUTE).forEach { status ->
            val shipments = shipmentRepository.getByStatus(status)
            shipments.forEach { shipment ->
                LOGGER.debug("Updating shipment '{}': {} --> {}", shipment.id, shipment.status, status.next)
                updateShipmentStatus.invoke(
                    UpdateShipmentStatusRequest(
                        shipmentId = shipment.id,
                        newStatus = shipment.status.next.toString(),
                        clientId = shipment.clientId
                    )
                )
            }
        }
    }

    companion object {
        val LOGGER = LoggerFactory.getLogger(ShipmentUpdateBatch::class.java)
    }

}