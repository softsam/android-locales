# Android Locales
Easily change the locale of your android device to automate your end to end tests.

Requires [adb](http://developer.android.com/tools/help/adb.html) to control the application.

## How to use
Download the latest apk available in the releases (or build the apk).

Install the apk

    adb install -R android_locales.apk

Grant the required permissions to the application

    adb shell pm grant com.orange.androidlocales android.permission.CHANGE_CONFIGURATION

> This is required since android 4.2 and changes to settings. More info [here](http://developer.android.com/about/versions/android-4.2.html).

Change the locale to whatever you want. Example setting fr_FR (French from France)

    adb shell am start -n com.orange.androidlocales/.ChangeLocaleActivity_ -e language fr -e country FR

> The application does not check if the language et country you pass are valid, and assumes you know what you are doing. If the change cannot be made for any reason, the application will silently fail, and terminate.

## How to build
Make sure you have all the [tools](https://developer.android.com/sdk/index.html) needed to build an android application.

Clone the repository

    git clone https://github.com/softsam/android-locales.git

And build

    gradlew clean build

The produced apk will be placed in the _app/build/outputs/apk_ directory.

## Licence

MIT Licence (See LICENCE file)