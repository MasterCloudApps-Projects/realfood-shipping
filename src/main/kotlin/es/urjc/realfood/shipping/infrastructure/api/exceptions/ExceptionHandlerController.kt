package es.urjc.realfood.shipping.infrastructure.api.exceptions

import es.urjc.realfood.shipping.domain.exceptions.ShipmentNotFoundException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler


@ControllerAdvice
class ExceptionHandlerController {

    private val exceptionsMap = mapOf(
        ShipmentNotFoundException::class.java to 404
    )

    @ExceptionHandler
    fun handle(exception: Exception): ResponseEntity<ApiError> {
        return ResponseEntity.status(exceptionsMap.getOrDefault(exception::class.java, 500))
            .body(ApiError(exception.message ?: "No message available"))

    }
}