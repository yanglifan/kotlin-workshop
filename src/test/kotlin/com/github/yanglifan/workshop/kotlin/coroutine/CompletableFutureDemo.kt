package com.github.yanglifan.workshop.kotlin.coroutine

import kotlinx.coroutines.experimental.future.await
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Test
import org.slf4j.LoggerFactory
import java.util.concurrent.CompletableFuture

class CompletableFutureDemo {
    private val log = LoggerFactory.getLogger(javaClass)

    @Test
    fun future_with_old_way() {
        val future = CompletableFuture.supplyAsync({ 1 })
        future.thenApply { value -> "${value + 2}" }
                .thenAccept({ value ->
            log.info(value.toString())
        })
    }

    @Test
    fun future_with_coroutine() {
        runBlocking {
            val future = CompletableFuture.supplyAsync({ 1 })
            val value = future.await()
            val result = value + 2
            log.info(result.toString())
        }
    }
}