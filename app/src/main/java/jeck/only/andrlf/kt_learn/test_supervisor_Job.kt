package jeck.only.andrlf.kt_learn

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by JeckOnly on 2025/3/25
 * Describe:
 */

fun main() {
    CoroutineScope(Dispatchers.IO).launch {
        val supervisorJob = SupervisorJob()
        launch(supervisorJob) {// supervisorJob only has 1 child
            // ---------------------------
            val launchJob = coroutineContext[Job]
            val launchJobParent = launchJob?.parent
            println("launchJob: $launchJob, launchJobParent: $launchJobParent, supervisorJob: $supervisorJob")
            // ---------------------------
            launch {
                delay(2000)
                println("2")
            }
            launch {
                delay(2000)
                println("4")
            }
            launch {

                launch {
                    delay(1200)
                    println("5")
                }
                launch {
                    delay(1200)
                    println("7")
                }
                launch {
                    delay(1100)
                    println("6")
                    throw IllegalStateException()
                }
            }
        }
    }

    Thread.sleep(999999)
}