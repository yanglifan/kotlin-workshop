package com.github.yanglifan.workshop.kotlin.coroutine

import kotlinx.coroutines.experimental.future.await
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Test
import org.slf4j.LoggerFactory
import java.util.concurrent.CompletableFuture

class FutureDemo {
    private val log = LoggerFactory.getLogger(javaClass)

    @Test
    fun future_with_old_way() {
        val future = CompletableFuture.supplyAsync({ 100 })
        future.thenAccept({ value ->
            log.info(value.toString())
        })
    }

    @Test
    fun future_with_coroutine() {
        runBlocking {
            val future = CompletableFuture.supplyAsync({ 100 })
            val value = future.await()
            log.info(value.toString())
        }
    }
}