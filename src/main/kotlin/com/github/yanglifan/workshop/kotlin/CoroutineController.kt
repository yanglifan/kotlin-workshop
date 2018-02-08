package com.github.yanglifan.workshop.kotlin

import com.github.yanglifan.workshop.kotlin.persistence.ReactiveAuditRepository
import com.github.yanglifan.workshop.kotlin.persistence.ReactiveMessageRepository
import com.github.yanglifan.workshop.kotlin.persistence.ReactivePeopleRepository
import kotlinx.coroutines.experimental.Unconfined
import kotlinx.coroutines.experimental.reactive.awaitFirstOrDefault
import kotlinx.coroutines.experimental.reactive.awaitSingle
import kotlinx.coroutines.experimental.reactor.mono
import lombok.extern.slf4j.Slf4j
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@Slf4j
@RestController
class CoroutineController(
        val peopleRepository: ReactivePeopleRepository,
        val auditRepository: ReactiveAuditRepository,
        val messageRepository: ReactiveMessageRepository) {
    @GetMapping("/coroutine/{personId}")
    fun getNumberOfMessages(@PathVariable personId: String) = mono(Unconfined) {
        val person = peopleRepository.findById(personId).awaitFirstOrDefault(null)
                ?: throw NoSuchElementException("No person can be found by $personId")

        val lastLoginDate = auditRepository.findByEmail(person.email).awaitSingle().eventDate

        val numberOfMessages =
                messageRepository.countByMessageDateGreaterThanAndEmail(lastLoginDate, person.email).awaitSingle()

        "Hello ${person.name}, you have $numberOfMessages messages since $lastLoginDate"
    }
}