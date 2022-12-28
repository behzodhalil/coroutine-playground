package basic.example_1

import kotlinx.coroutines.*

fun main() {
    val job  = Job()
    val jobA = Job(job)

    if (job.isActive) {
        println("job is active")
    }

    if (job.isCompleted) {
        println("job is completed")
    }

    if (job.isCancelled) {
        println("job is cancelled")
    }


    if (jobA.isActive) {
        println("job a is active")
    }

    if (jobA.isCompleted) {
        println("job a is completed")
    }

    if (jobA.isCancelled) {
        println("job a is cancelled")
    }

    val scopeA = CoroutineScope(job).launch {
        // when you launch a coroutine,
        // we create Job, which is always new state
        println("scope a is running")
    }

    val scopeB = CoroutineScope(jobA).launch {
        // it moves to active state, when we use join or start
        println("scope b is running")
    }

    scopeB.start()

    if (scopeB.isActive) {
        // it runs before cancel own state
        scopeA.cancelChildren()
    }

}