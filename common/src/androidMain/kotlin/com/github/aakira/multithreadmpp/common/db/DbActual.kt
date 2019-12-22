package com.github.aakira.multithreadmpp.common.db

import android.content.Context
import com.github.aakira.multithreadmpp.db.GreetingDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver

// db
lateinit var mppAppContext: Context

actual fun createDb(): GreetingDatabase? =
    GreetingDatabase(AndroidSqliteDriver(GreetingDatabase.Schema, mppAppContext, dbName))
