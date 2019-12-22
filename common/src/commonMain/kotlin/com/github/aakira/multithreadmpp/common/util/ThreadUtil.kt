package com.github.aakira.multithreadmpp.common.util

import kotlinx.coroutines.CoroutineDispatcher

internal expect val backgroundDispatcher: CoroutineDispatcher

internal expect fun isMainThread(): Boolean

internal expect fun currentThreadName(): String

internal fun assertNotMainThread() {
    if (isMainThread())
        throw IllegalStateException("This is main thread")
}

expect fun <T> T.freeze(): T
