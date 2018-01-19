package com.github.yanglifan.kotlinworkshop

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinWorkshopApplication

fun main(args: Array<String>) {
    runApplication<KotlinWorkshopApplication>(*args)
}
