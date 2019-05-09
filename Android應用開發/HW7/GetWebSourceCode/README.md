GetWebSourceCode
=========================

An app that retrieves and displays the contents of a web page that's located at a URL

Introduction
------------

This app created from the empty Activity template. 
The app use an AsyncTaskLoader to retrieve the source code of the web page at the URL.

Reminders
--------
- Please use **spinner** to that allows the user to choose the protocol (HTTP or HTTPS)
- Please displays the results of retrieving the source of the web page in a TextView in a ScrollView **in the same activity**.
- Please **turn your phone/emulator into airplane mode** before executing `NoInternetTest`

## GetWebSourceCodeTest

### `@RunWith(AndroidJUnit4.class) public class GetWebSourceCodeTest`

Tests for the Homework "Build and run an app" in Android fundamentals 07.2

 * **See also:** <a href="https://codelabs.developers.google.com/codelabs/android-training-asynctask-asynctaskloader/index.html?index=..%2F..%2Fandroid-training#10">07.2: AsyncTask and AsyncTaskLoader</a>

### `@Test public void testWebSourceCodeLayout()`

07.2 Create an app that retrieves and displays the contents of a web page that's located at a URL. The app displays the following: - A field in which the user enters a URL - field such as a menu or spinner that allows the user to choose the protocol (HTTP or HTTPS) - A button that executes the task when the user taps it - A scrolling display of the source code of the web page at the URL

### `@Test public void testWebSourceCodeFunction()`

07.2 The display must contain a TextView in a ScrollView that displays the source code. (http and https)

---
## NoInternetTest

### `@RunWith(AndroidJUnit4.class)  public class NoInternetTest`

Tests for the Homework "Build and run an app" in Android fundamentals 07.2

 * **See also:** <a href="https://codelabs.developers.google.com/codelabs/android-training-asynctask-asynctaskloader/index.html?index=..%2F..%2Fandroid-training#10">07.2: AsyncTask and AsyncTaskLoader</a>

### `@Test public void testNoInternetResponse()`

07.2 If connection to the internet is not available when the user taps the button, the app must show the user an appropriate response. For example, the app might display a message such as "Check your internet connection and try again."