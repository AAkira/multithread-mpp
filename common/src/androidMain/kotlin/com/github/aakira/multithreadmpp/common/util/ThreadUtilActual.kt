package com.github.aakira.multithreadmpp.common.util

import android.os.Looper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

internal actual val backgroundDispatcher: CoroutineDispatcher = Dispatchers.IO

internal actual fun isMainThread(): Boolean = Looper.getMainLooper() === Looper.myLooper()

internal actual fun currentThreadName(): String = Thread.currentThread().name

actual fun <T> T.freeze(): T = this
