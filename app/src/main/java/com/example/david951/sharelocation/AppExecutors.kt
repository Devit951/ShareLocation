package com.example.david951.sharelocation

import android.os.Handler
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class AppExecutors private constructor(val ioExecutor: Executor , val mainThreadExecutor : Executor) {

    constructor() : this(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()) , MainThreadExecutor())

    fun mainThread() : Executor{
        return mainThreadExecutor
    }

    fun ioThread() : Executor{
        return ioExecutor
    }

    private class MainThreadExecutor : Executor {
        private val handler = Handler()

        override fun execute(p: Runnable?) {
            p?.let { handler.post(it) }
        }
    }
}