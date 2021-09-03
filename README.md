# DataCollector Android SDK

The DataCollector SDK collects and sends device information to improve fraud detection accuracy, and
identify devices.

## Table of Contents

1. [ Requirements ](#markdown-header-requirements)
1. [ App permissions ](#markdown-header-app-permissions)
3. [ Installation ](#markdown-header-installation)
4. [ How to use ](#markdown-header-how-to-use)
5. [ Switching environments ](#markdown-header-switching-environments)
7. [ Java Example ](#markdown-header-java-example)
8. [ Report Issues ](#markdown-header-report-issues)
9. [ License ](#markdown-header-license)

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

New releases of the DataCollector Android SDK are published via [Maven Repository](https://repo1.maven.org/maven2/com/dlocal/android/data-collector/). 
The latest version is available via `mavenCentral()`.

Add `mavenCentral()` to the project level `build.gradle` file's repositories section:
```groovy

repositories {
    mavenCentral()
    ...
}

```

Add DataCollector SDK dependency to the application's `build.gradle` file:
```groovy

dependencies {
    ...
    implementation 'com.dlocal.android:data-collector:0.0.2'
    ...
}

```

## How to use

### 1) Configure SDK

Create an Application file if you haven't already and initialize the sdk calling setUp method and passing your API key:

```kotlin
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // Setup for DLocal Data Collector SDK
        DLCollector.setUp(this, DLSettings("YOUR_API_KEY"))
    }
}
```

Optionally you can configure the environment and the desired log level in the attributes of the `DLSettings` object:

```kotlin
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val settings = DLSettings(
            apiKey = "YOUR_API_KEY",
            environment = DLEnvironment.SANDBOX,
            logLevel = DLLogLevel.VERBOSE
        )
        DLCollector.setUp(this, settings)
    }
}
```
Replacing `apiKey` with your key. 

See the [SampleApplication](https://bitbucket.org/dlocal-public/data-collector-sdk-android/src/master/public/app/src/main/java/com/dlocal/sampleapp/SampleApplication.kt) for a detailed example.

### 2) Start Session

Start session will gather device information, and generate a sessionId.

This step can be done any time, but **it's recommended to call it as soon as a session is present in your application state.**:

```kotlin
DLCollector.getInstance().startSession()
```

You can also associate additional data related to each session to improve the fraud prevention score. The following example shows how to pass the user's ID inside `DLAdditionalData` object.

```kotlin
val additionalData = DLAdditionalData(userId = "USER_ID")
DLCollector.getInstance().startSession(additionalData)
```

> NOTE: This method runs in a background thread and doesn't block the main thread.

See the SampleApp [SampleActivity](https://bitbucket.org/dlocal-public/data-collector-sdk-android/src/master/public/app/src/main/java/com/dlocal/sampleapp/SampleActivity.kt) for a detailed example.

### 3) Link the session to the transaction

When the user starts the checkout transaction, gather the session id like so:

```kotlin
val sessionId: String? = DLCollector.getInstance().getSessionId()
```

Send that id in the transaction as additional info. The method can return null if a session is not available or an error occurred.

## Switching environments

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

- [Set up - JavaSampleApplication](https://bitbucket.org/dlocal-public/data-collector-sdk-android/src/master/public/app/src/main/java/com/dlocal/sampleapp/JavaSampleApplication.java)

- [Start session - JavaSampleActivity](https://bitbucket.org/dlocal-public/data-collector-sdk-android/src/master/public/app/src/main/java/com/dlocal/sampleapp/JavaSampleActivity.java#lines-34)

- [Get session ID - JavaSampleActivity](https://bitbucket.org/dlocal-public/data-collector-sdk-android/src/master/public/app/src/main/java/com/dlocal/sampleapp/JavaSampleActivity.java#lines-39)

## Report Issues

If you have a problem or find an issue with the SDK please contact us at [mobile-dev@dlocal.com](mailto:mobile-dev@dlocal.com).

## License
