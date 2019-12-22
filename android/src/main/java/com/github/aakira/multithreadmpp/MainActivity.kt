package com.github.aakira.multithreadmpp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.github.aakira.multithreadmpp.common.GreetingService
import com.github.aakira.multithreadmpp.common.db.mppAppContext
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // initialize mpp
        mppAppContext = this.applicationContext

        val service = GreetingService()
        // store value
        service.saveGreetings("Hello")
        // observe db value
        service.getGreetings()
            .onEach { query ->
                Log.v("Android", "Android Main Thread: ${Thread.currentThread().name}")
                query.executeAsList().firstOrNull()?.let {
                    helloText.text = it.hello
                }
            }
            .catch {
                helloText.text = it.toString()
            }
            .launchIn(lifecycleScope)
    }
}
