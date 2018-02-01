package com.github.yanglifan.workshop.kotlin

import com.github.yanglifan.workshop.kotlin.persistence.ReactiveAuditRepository
import com.github.yanglifan.workshop.kotlin.persistence.ReactiveMessageRepository
import com.github.yanglifan.workshop.kotlin.persistence.ReactivePeopleRepository
import kotlinx.coroutines.experimental.Unconfined
import kotlinx.coroutines.experimental.reactive.awaitSingle
import kotlinx.coroutines.experimental.reactor.mono
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class CoroutineController(
        val peopleRepository: ReactivePeopleRepository,
        val auditRepository: ReactiveAuditRepository,
        val messageRepository: ReactiveMessageRepository) {
    @GetMapping("/coroutine/{personId}")
    fun getNumberOfMessages(@PathVariable personId: String) = mono(Unconfined) {
        val person = peopleRepository.findById(personId).awaitSingle()

        val lastLoginDate = auditRepository.findByEmail(person.email).awaitSingle().eventDate

        val numberOfMessages =
                messageRepository.countByMessageDateGreaterThanAndEmail(lastLoginDate, person.email).awaitSingle()

        "Hello ${person.name}, you have $numberOfMessages messages since $lastLoginDate"
    }
}