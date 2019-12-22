package com.github.aakira.multithreadmpp.common

import com.github.aakira.multithreadmpp.common.model.Greeting
import com.github.aakira.multithreadmpp.common.util.currentThreadName
import com.github.aakira.multithreadmpp.common.util.doOnNext
import com.github.aakira.multithreadmpp.common.util.mainScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@UseExperimental(ExperimentalCoroutinesApi::class)
fun GreetingService.getGreetings(
    successCallback: (Greeting) -> Unit,
    errorCallback: (Throwable) -> Unit
) {
    getGreetings()
        .doOnNext {
            println("iOS Actual Thread: " + currentThreadName())
        }
        .onEach {
            it.executeAsList()
                .firstOrNull()
                ?.let(successCallback)
        }
        .catch {
            errorCallback(it)
        }
        .launchIn(mainScope)
}
