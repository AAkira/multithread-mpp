package com.github.aakira.multithreadmpp.common.db

import com.github.aakira.multithreadmpp.common.model.Greeting
import com.github.aakira.multithreadmpp.common.util.freeze
import com.github.aakira.multithreadmpp.db.GreetingDatabase
import com.squareup.sqldelight.Query
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

expect fun createDb(): GreetingDatabase?

internal const val dbName: String = "greeting.db"

class GreetingDao {

    private val greetingDatabase = createDb()
    private val queries = greetingDatabase?.greetingQueries

    fun storeGreeting(hello: String) {
        queries?.insertItem(hello)
    }

    fun getGreetings(): Flow<Query<Greeting>> {
        val queries = queries ?: return flowOf()

        return queries
            .selectAll(mapper = { _, hello -> Greeting(hello) })
            .asFlow()
    }
}

internal fun <T : Any> Query<T>.asFlow(): Flow<Query<T>> = flow {
    emit(this@asFlow)

    val channel = Channel<Unit>(Channel.CONFLATED).freeze()
    val listener = object : Query.Listener {
        override fun queryResultsChanged() {
            channel.offer(Unit)
        }
    }

    addListener(listener)
    try {
        for (item in channel) {
            emit(this@asFlow)
        }
    } finally {
        removeListener(listener)
    }
}
