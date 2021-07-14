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
