package com.github.aakira.multithreadmpp.common

import com.github.aakira.multithreadmpp.common.db.GreetingDao
import com.github.aakira.multithreadmpp.common.model.Greeting
import com.github.aakira.multithreadmpp.common.util.assertNotMainThread
import com.github.aakira.multithreadmpp.common.util.backgroundDispatcher
import com.github.aakira.multithreadmpp.common.util.currentThreadName
import com.github.aakira.multithreadmpp.common.util.doOnNext
import com.squareup.sqldelight.Query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class GreetingService {

    private val dao = GreetingDao()

    fun saveGreetings(greeting: String) {
        dao.storeGreeting(greeting)
    }

    fun getGreetings(): Flow<Query<Greeting>> =
        dao.getGreetings()
            .doOnNext {
                assertNotMainThread()

                println("----------------")
                println("Common Background Thread: ${currentThreadName()}")
                println("----------------")
            }
            .flowOn(backgroundDispatcher)
}
