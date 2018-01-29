package com.github.yanglifan.workshop.kotlin.coroutine

import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Test
import org.slf4j.LoggerFactory
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class SimpleDemo {
    private val log = LoggerFactory.getLogger(javaClass)

    @Test
    fun delay_with_coroutine() {
        runBlocking {
            log.info("Hello")
            delay(1000)
            log.info("World")
        }
    }

    @Test
    fun delay_with_thread() {
        log.info("Hello")
        Thread.sleep(1000)
        log.info("World")
    }

    @Test
    fun delay_with_scheduler() {
        val scheduler = Executors.newScheduledThreadPool(1)
        log.info("Hello")
        scheduler.schedule({
            log.info("World")
        }, 1, TimeUnit.SECONDS)
        scheduler.shutdown()
        scheduler.awaitTermination(1, TimeUnit.SECONDS)
    }

    @Test
    fun delay_with_launch() {
        runBlocking {
            log.info("Hello")
            val job = launch {
                // launch new coroutine and keep a reference to its Job
                delay(5000L)
                log.info("World")
            }
            job.cancel()
            job.join() // wait until child coroutine completes
        }
    }

    @Test
    fun delay_with_async() {
        log.info("Start to demo async")

        val one = async {
            delay(1000)
            1
        }

        val two = async {
            delay(2000)
            2
        }

        runBlocking { log.info("${one.await() + two.await()}") }
    }
}