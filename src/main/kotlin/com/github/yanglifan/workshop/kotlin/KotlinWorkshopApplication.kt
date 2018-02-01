package com.github.yanglifan.workshop.kotlin

import com.github.yanglifan.workshop.kotlin.persistence.*
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.time.Clock
import java.time.LocalDateTime

@SpringBootApplication
class KotlinWorkshopApplication(
        val peopleRepository: PeopleRepository,
        val auditRepository: AuditRepository,
        val messageRepository: MessageRepository) : CommandLineRunner {
    override fun run(vararg args: String?) {
        val clock = Clock.systemDefaultZone()

        peopleRepository.save(Person("P1", "Alex", "alex@example.com"))

        auditRepository.save(Audit(LocalDateTime.now(clock).minusDays(2), "alex@example.com"))

        messageRepository.save(Message("Hello", LocalDateTime.now(clock).minusDays(10), "alex@example.com"))
        messageRepository.save(Message("How are you", LocalDateTime.now(clock), "alex@example.com"))
    }
}

fun main(args: Array<String>) {
    runApplication<KotlinWorkshopApplication>(*args)
}
