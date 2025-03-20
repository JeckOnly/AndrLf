package jeck.only.andrlf.kt_learn

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {

    runBlocking {
        try {
            launch {
                delay(1000)
                throw Exception("launch")
            }
        } catch (e: Exception) {
            println("catch: $e")
        } finally {
            println("finally")
        }

        launch {
            delay(2000)
            println("will not printed")
        }
    }
}