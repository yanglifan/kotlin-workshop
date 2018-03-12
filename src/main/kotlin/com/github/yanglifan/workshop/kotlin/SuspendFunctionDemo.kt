package com.github.yanglifan.workshop.kotlin

import kotlinx.coroutines.experimental.delay

suspend fun methodA(): String {
    val value = methodB()
    methodD(value)
    return value
}

suspend fun methodB(): String {
    delay(100)
    return methodC("world")
}

suspend fun methodC(value: String): String {
    delay(100)
    return "hello $value"
}

suspend fun methodD(value: String) {
    delay(100)
    println(value)
}