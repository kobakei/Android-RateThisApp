Android-RateThisApp
===================

Android-RateThisApp is an library to show "Rate this app" dialog.

The library monitors the following status

* How many times is the app launched
* How long days does it take from the app installation

and show a dialog to engage users to rate the app in Google Play.

## How to use

### Download
```sh
git clone git://github.com:kskkbys/Android-RateThisApp.git
```

### Setting on Eclipse
Import Android-RateThisApp/library in Eclispe. Then, link your application project with this library.

### Implementation
Call `RateThisApp.onAppLaunched(Context)` and `RateThisApp.showRateDialogIfNeeded(Context)` in your launcher activity's onStart() method.
```java
@Override
protected void onStart() {
    super.onStart();
    
    // Monitor launch times and interval from installation
    RateThisApp.onAppLaunched(this);
    // If the criteria is satisfied, "Rate this app" dialog will be shown
    RateThisApp.showRateDialogIfNeeded(this);
}
```

### Custom criteria
The default criteria to show the dialog is as below:

* App is launched more than 10 times
* App is launched more than 7 days later than installation.

If you want to use your own criteria, please edit constants in RateThisApp.java.

## Contribute this project
If you want to contribute this project, please send pull request.
In present, I need contributors who can translate resources from English/Japanese into Spanish, Chinese and other languages.

## License
This software is licensed under [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0.html)

## Author
Keisuke Kobayashi - k.kobayashi.122@gmail.com
