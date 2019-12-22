package com.github.aakira.multithreadmpp.common.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import platform.Foundation.NSThread
import kotlin.coroutines.CoroutineContext
import kotlin.native.concurrent.freeze

@UseExperimental(ExperimentalCoroutinesApi::class)
internal actual val backgroundDispatcher: CoroutineDispatcher = Dispatchers.Default

internal actual fun isMainThread(): Boolean = NSThread.isMainThread

internal actual fun currentThreadName(): String = NSThread.currentThread.toString()

internal val mainScope = ModelScope(Dispatchers.Main)

internal class ModelScope(private val mainContext: CoroutineContext) : CoroutineScope {
    private val job = Job()
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        println("Error $throwable")
    }

    override val coroutineContext: CoroutineContext
        get() = mainContext + job + exceptionHandler
}

actual fun <T> T.freeze(): T = freeze()
