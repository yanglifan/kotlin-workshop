package com.github.yanglifan.workshop.kotlin.coroutine

import org.junit.Test
import org.slf4j.LoggerFactory
import java.util.concurrent.CompletableFuture

class FutureDemo {
    private val log = LoggerFactory.getLogger(javaClass)

    @Test
    fun future_with_old_way() {
        val future = CompletableFuture.supplyAsync({100})
        future.thenAccept({ value ->
            log.info(value.toString())
        })
    }
}