# Makitoo android example

## Introduction
Makitoo is a technology for monitoring Android applications in production. It allows you to :
- Remotely monitor your application. Each exception at runtime is automatically monitored in the Spoonware dashboard.
- Diagnostic all your bugs with automatically collected information.
- Fix some bugs at runtime with hot patching, without recompiling and re-deploing your application.

This repository is an example Android built with gradle. This app has one single screen with two buttons, we inserted on purpose a bug causing a crash in the "back-up notes" button.

## Usage

First, you should create a Makitoo account : <https://dashboard.makitoo.com/>

When you are successfully logged in, you can create an application, it gives you get an ID and TOKEN of your application.

Then, edit file `app/src/main/java/com/moonpi/swiftnotes/MainActivity.java` to insert your application id and token in onCreate() method as follows.

    com.makitoo.MakitooAndroid.init(this,
      "https://dashboard.makitoo.com/rest",
      "APPLICATION_ID",
      "TOKEN");

Ensure your have the INTERNET permission in your app's AndroidManifest.xml file.

## Execution

Install your application on a connected device with :
`$ ./gradlew clean installDebug

The application is now installed and collect execution bugs in your dashboard at <https://dashboard.makitoo.com/>.

## Including Makitoo in your own application

To include makitoo in your own Android application, you need to :

### Add makitoo libraries in your Gradle project classpath
```groovy
repositories {
    maven {
        url 'https://dashboard.makitoo.com/repo'
    }
}

dependencies {
    compile 'com.makitoo:makitoo-library-android:0.0.1'
}
```

### Configure Makitoo in your application's onCreate method

```java
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        com.makitoo.MakitooAndroid.init(this,
                "https://dashboard.makitoo.com/rest",
                "APPLICATION_ID",
                "TOKEN");
    }
}
```

### Activate the Makitoo instrumentation (a gradle plugin) in your Gradle build script
```groovy
buildscript {
    repositories {
        maven {
            url 'https://dashboard.makitoo.com/repo'
        }
    }
    dependencies {
        classpath 'com.makitoo:makitoo-gradle-plugin:0.0.1'
    }
}

apply plugin: 'makitoo'

makitoo {
    modules = [
            'com.makitoo.handled.HandledModule',
            'com.makitoo.hotcatches.HotFixesModule',
            'com.makitoo.npe.NpeModule',
            'com.makitoo.loop.LoopModule'
    ]
}
```

Recompile, reinstall and voila!

`$ ./gradlew clean installDebug`

Now the monitoring information goes to the monitoring server and you can access it at <https://dashboard.makitoo.com/>

Ensure your have the INTERNET permission in your app's AndroidManifest.xml file: the app needs to be able to send reports to our servers.

# Limitations

Makitoo plugin doesn't (yet) support Android project with multiples variants.
