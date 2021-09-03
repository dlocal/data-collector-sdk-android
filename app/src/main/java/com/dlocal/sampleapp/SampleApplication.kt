package com.dlocal.sampleapp

import android.app.Application
import com.dlocal.datacollector.DLCollector
import com.dlocal.datacollector.api.DLEnvironment
import com.dlocal.datacollector.api.DLLogLevel
import com.dlocal.datacollector.api.DLSettings

@Suppress("unused")
class SampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Setup for DLocal Data Collector SDK
        val settings = DLSettings(
            apiKey = "API KEY",
            environment = DLEnvironment.SANDBOX,
            logLevel = DLLogLevel.SILENT
        )
        DLCollector.setUp(this, settings)
    }
}