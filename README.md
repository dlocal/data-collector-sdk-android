# DataCollector Android SDK

The DataCollector SDK collects and sends device information to improve fraud detection accuracy, and
identify devices. Lightweight library with only `134KB` AAR file size.

## Table of Contents

1. [ Requirements ](#markdown-header-requirements)
2. [ App permissions ](#markdown-header-app-permissions)
3. [ Installation ](#markdown-header-installation)
4. [ How to use ](#markdown-header-how-to-use)
5. [ Testing the integration ](#markdown-header-testing-the-integration)
    - [ Switching environments ](#markdown-header-switching-environments)
6. [ Java Example ](#markdown-header-java-example)
7. [ Sample App ](#markdown-header-sample-app)
8. [ Cross-platform frameworks ](#markdown-header-cross-platform-frameworks)
9. [ Report Issues ](#markdown-header-report-issues)
10. [ License ](#markdown-header-license)

## Requirements

- Android Studio 4.1+
- Supports API versions from 21 and higher.

## App permissions

```
android.permission.INTERNET (Optional)
android.permission.ACCESS_WIFI_STATE (Optional)
android.permission.ACCESS_NETWORK_STATE (Optional)
```

## Installation

New releases of the DataCollector Android SDK are published via [Maven Repository](https://mvnrepository.com/artifact/com.dlocal.android/data-collector).
The latest version is available via `mavenCentral()`.

Add `mavenCentral()` to the project level [build.gradle](https://bitbucket.org/dlocal-public/data-collector-sdk-android/src/master/build.gradle#lines-5) file's repositories section, if you don't have it already:
```groovy

repositories {
    mavenCentral()
    ...
}

```

Add DataCollector SDK dependency to the application's [build.gradle](https://bitbucket.org/dlocal-public/data-collector-sdk-android/src/master/app/build.gradle#lines-38) file:
```groovy

dependencies {
    ...
    implementation 'com.dlocal.android:data-collector:1.0.1'
    ...
}

```

## How to use

### 1) Configure SDK

Create an Application file if you haven't already and initialize the sdk calling setUp method and passing your API key:

```kotlin
import com.dlocal.datacollector.DLCollector
import com.dlocal.datacollector.api.DLSettings

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // Setup for DLocal Data Collector SDK
        DLCollector.setUp(this, DLSettings("API KEY"))
    }
}
```

Optionally you can configure the environment and the desired log level in the attributes of the `DLSettings` object:

```kotlin
import com.dlocal.datacollector.DLCollector
import com.dlocal.datacollector.api.DLEnvironment
import com.dlocal.datacollector.api.DLLogLevel
import com.dlocal.datacollector.api.DLSettings

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val settings = DLSettings(
            apiKey = "API KEY",
            environment = DLEnvironment.SANDBOX,
            logLevel = DLLogLevel.VERBOSE
        )
        DLCollector.setUp(this, settings)
    }
}
```
Replacing `apiKey` with your key.

See the [SampleApplication](https://bitbucket.org/dlocal-public/data-collector-sdk-android/src/master/app/src/main/java/com/dlocal/sampleapp/SampleApplication.kt) for a detailed example.

### 2) Start Session

Start session will gather device information, and generate a sessionId.

This step can be done any time, but **it's recommended to call it as soon as a session is present in your application state.**:

```kotlin
DLCollector.startSession()
```

You can also associate additional data related to each session to improve the fraud prevention score. The following example shows how to pass the user reference ID inside `DLAdditionalData` object.

```kotlin
val additionalData = DLAdditionalData(userReference = "user-id")
DLCollector.startSession(additionalData)
```

> NOTE: This method runs in a background thread and doesn't block the main thread.

See the SampleApp [SampleActivity](https://bitbucket.org/dlocal-public/data-collector-sdk-android/src/master/app/src/main/java/com/dlocal/sampleapp/SampleActivity.kt) for a detailed example.

### 3) Link the session to the transaction

When the user starts the checkout transaction, gather the session id like so:

```kotlin
val sessionId: String? = DLCollector.getSessionId()
```

Submit this value in the payment request within the `additional_risk_data.device.event_uuid` parameter. The method can return null if a session is not available or an error occurred.

## Testing the integration

Once integrated, you can use the `DLLogLevel.VERBOSE` log level to see if the SDK is working. This can be done by changing the setup settings like so:

```kotlin
val settings = DLSettings(apiKey = "SBX API KEY", environment = DLEnvironment.SANDBOX, logLevel = DLLogLevel.VERBOSE)
```

And looking at the console, when startSession is run, we should see the following logs if everything is working:

```log
I/DLDataCollector [SANDBOX]: Collected 57 data points in 646 milliseconds
I/DLDataCollector [SANDBOX]: POST success with sessionId 177eb063-bf2d-4247-8db2-66b87409419e
```

### Switching environments

We strongly **recommend that you use the `SANDBOX` environment when testing**, and only use `PRODUCTION` in production ready builds.

To do so, you can use the setup and `DLCollectorSettings` to configure a different environment, i.e:

```kotlin
val settings = if (BuildConfig.DEBUG) {
    DLSettings(
        apiKey = "SBX API KEY",
        environment = DLEnvironment.SANDBOX,
        logLevel = DLLogLevel.VERBOSE
    )
} else {
    DLSettings(
        apiKey = "PROD API KEY",
        environment = DLEnvironment.PRODUCTION,
        logLevel = DLLogLevel.SILENT
    )
}
```

Replacing the `apiKey` with yours for each environment.

## Java Examples

You can use the SDK from Java due to the interoperability between Java and Kotlin, checkout the sample app's Java examples.

- [Set up - JavaSampleApplication](https://bitbucket.org/dlocal-public/data-collector-sdk-android/src/master/app/src/main/java/com/dlocal/sampleapp/JavaSampleApplication.java)

- [Start session - JavaSampleActivity](https://bitbucket.org/dlocal-public/data-collector-sdk-android/src/master/app/src/main/java/com/dlocal/sampleapp/JavaSampleActivity.java#lines-34)

- [Get session ID - JavaSampleActivity](https://bitbucket.org/dlocal-public/data-collector-sdk-android/src/master/app/src/main/java/com/dlocal/sampleapp/JavaSampleActivity.java#lines-39)

## Sample App

In this repository there's a [sample app](https://bitbucket.org/dlocal-public/data-collector-sdk-android/src/master/app/) to showcase how to use the SDK, please refer to the code for more detailed examples.

## Cross-platform frameworks
This SDK can be included in any native app, we also made available the following plugins for cross-platform frameworks:

- [ionic capacitor plugin](https://bitbucket.org/dlocal-public/dlocal-data-collector-capacitor-plugin/src/main/)

## Report Issues

If you have a problem or find an issue with the SDK please contact us at [mobile-dev@dlocal.com](mailto:mobile-dev@dlocal.com).

## [License](LICENSE)