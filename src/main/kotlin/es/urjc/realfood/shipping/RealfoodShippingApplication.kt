package es.urjc.realfood.shipping

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class RealfoodShippingApplication

fun main(args: Array<String>) {
    runApplication<RealfoodShippingApplication>(*args)
}
