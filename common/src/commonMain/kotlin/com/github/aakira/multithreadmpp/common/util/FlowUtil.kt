package com.github.aakira.multithreadmpp.common.util

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

@UseExperimental(ExperimentalCoroutinesApi::class)
inline fun <T> Flow<T>.doOnNext(crossinline block: suspend (T) -> Unit): Flow<T> =
    transform { value ->
        block(value)
        return@transform emit(value)
    }
