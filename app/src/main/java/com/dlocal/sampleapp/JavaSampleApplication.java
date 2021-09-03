package com.dlocal.sampleapp;

import android.app.Application;

import com.dlocal.datacollector.DLCollector;
import com.dlocal.datacollector.api.DLEnvironment;
import com.dlocal.datacollector.api.DLLogLevel;
import com.dlocal.datacollector.api.DLSettings;

@SuppressWarnings("unused")
public class JavaSampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Setup for DLocal Data Collector SDK
        DLSettings settings = new DLSettings(
                "API KEY",
                DLEnvironment.SANDBOX,
                DLLogLevel.SILENT
        );
        DLCollector.setUp(this, settings);
    }
}
