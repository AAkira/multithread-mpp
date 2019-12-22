package com.github.aakira.multithreadmpp.common.db

import com.github.aakira.multithreadmpp.db.GreetingDatabase
import com.squareup.sqldelight.drivers.ios.NativeSqliteDriver

actual fun createDb(): GreetingDatabase? =
    GreetingDatabase(NativeSqliteDriver(GreetingDatabase.Schema, dbName))
