# Change Log

## 1.2.0 (2017/04/27)

- New feature
  - `RateThisApp.onStart` is deprecated. Please call `RateThisApp.onCreate` instead.
- Other
  - Refactoring
  - SDK update

## 1.1.3 (2017/03/23)

- New feature
  - Add hu and az translations
- Other
  - Update SDK version and support lib

## 1.1.2 (2017/02/08)

- New feature
  - Add `getLaunchCount()`
- Other
  - Update SDK version and support lib
  - Add unit tests

## 1.1.1 (2017/01/13)

- New feature
  - Add cancelable option
- Fix
  - Fix dutch translation

## 1.1.0 (2016/09/26)

- New feature
  - Add nl translation
  - Add `Config.setUrl(String)`. This url is used to create intent when users click rate button.
- Other
  - Update gradle, support lib and SDK version. Now minSdkVersion = 9.

## 1.0.5 (2016/06/13)

- New feature
  - Add sk translation
  - Improve ar translation

## 1.0.4 (2016/06/08)

- New feature
  - Add it translation

## 1.0.3 (2016/04/11)

- New feature
  - Add eu translation
- Improvement
  - Use real install time from package manager
- Fix
  - Cancel callback is not invoked on pressing back key or touching outside.
  - Stacking of multiple rate dialogs

## 1.0.2 (2016/03/30)

- New feature
  - Add uk translation
  - Enable to opt out dialog by `stopRateDialog`
  - Enable to change button texts

## 1.0.1 (2016/03/12)

- New feature
  - Add pt & pt-rBR translation
  - Enable to set dialog theme
  - Support callback
- Fix
  - Opt out flag may not be set
- Important notice
  - Now project is uploaded through Bintray jcenter. **Group ID has been changed from `com.kobakei` to `io.github.kobakei`.**
