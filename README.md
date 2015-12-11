Android-RateThisApp
===================

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Android--RateThisApp-green.svg?style=true)](https://android-arsenal.com/details/1/2893)

Android-RateThisApp is an library to show "Rate this app" dialog.

![Screen shot](https://raw.github.com/kobakei/Android-RateThisApp/master/screenshot_resized.png)

The library monitors the following status

* How many times is the app launched
* How long days does it take from the app installation

and show a dialog to engage users to rate the app in Google Play.

## Getting Started

### Dependency

```
dependencies {
    compile 'com.kobakei:ratethisapp:0.0.4'
}
```

### Implementation

Call `RateThisApp.onStart(Context)` and `RateThisApp.showRateDialogIfNeeded(Context)` in your launcher activity's onStart() method.

```java
@Override
protected void onStart() {
    super.onStart();

    // Monitor launch times and interval from installation
    RateThisApp.onStart(this);
    // If the criteria is satisfied, "Rate this app" dialog will be shown
    RateThisApp.showRateDialogIfNeeded(this);
}
```

### Custom criteria

The default criteria to show the dialog is as below:

* App is launched more than 10 times
* App is launched more than 7 days later than installation.

If you want to use your own criteria, please call `RateThisApp.init(Configuration)` in your Application or launcher activity onCreate method.

```java
// Custom criteria: 3 days and 5 launches
RateThisApp.Config config = new RateThisApp.Config(3, 5);
// Custom title and message
config.setTitle(R.string.my_own_title);
config.setMessage(R.string.my_own_message);
RateThisApp.init(config);
```

## Contribute this project

If you want to contribute this project, please send pull request.
In present, I need contributors who can translate resources from English/Japanese into Spanish, Chinese and other languages.

## License

```
Copyright 2013-2015 Keisuke Kobayashi

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

## Author

Keisuke Kobayashi - k.kobayashi.122@gmail.com
