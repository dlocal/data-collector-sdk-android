# DataCollector Android SDK

The DataCollector Android SDK collects and sends Android device information and application interaction events to improve fraud detection accuracy.

Supports API versions from 21 (Android 5.0) and higher.

## Installation Android

Add mavenCentral() to the project level build.gradle file’s repositories section:

```groovy
repositories {
  mavenCentral()
  ...
}
```

Add DataCollector SDK dependency to the application’s build.gradle file:

```groovy
dependencies {
  ...
  implementation 'com.dlocal.android:data-collector:1.0.0'
  ...
}
```

## Application Integration

Create an Application file if you haven’t already and initialize the sdk calling setUp method and passing your API key:

```kotlin
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // Setup for DLocal Data Collector SDK
        DLCollector.setUp(this, DLSettings("YOUR_API_KEY"))
    }
}
```

Optionally you can configure the environment and the desired log level in the attributes of the DLSettings object:

```kotlin
DLSettings(
  apiKey = "test_api_key",
  environment = DLEnvironment.SANDBOX,
  logLevel = DLLogLevel.VERBOSE
)
```