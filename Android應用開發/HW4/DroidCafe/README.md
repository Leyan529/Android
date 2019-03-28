DroidCafe - Solution Code
=========================

App that demonstrates images used as buttons and a floating action button
to use an intent to launch a second activity.

Introduction
------------

This app created from the Basic Activity template lets a user tap an image
to make a choice. The app also uses an intent so that when the user taps the floating
action button, it launches the second Activity.

## Documentation

### `@RunWith(AndroidJUnit4.class) public class MainActivityTest`

Tests for the Homework "Change an app" in Android fundamentals 04.1 Please implement in @link{com.example.android.droidcafe.MainActivity}.

 * **See also:** <a href="https://codelabs.developers.google.com/codelabs/android-training-clickable-images/index.html?index=..%2F..%2Fandroid-training#9">04.1: Clickable images</a>

#### `@Test public void testHorizontalLayout()`

04.1 <br>
[2] Create a layout variant for horizontal orientation: content_main.xml (land). <br>
[3] Remove constraints from the three images and three text descriptions. <br>
[4] Select all three images in the layout variant, and choose Expand Horizontally in the Pack button to evenly distribute the images across the screen as shown in the figure below. <br>
[5]Constrain the text descriptions to the sides and bottoms of the images as shown in the figure below.


### `@RunWith(AndroidJUnit4.class) public class CheckboxesActivityTest`

Tests for the Homework "Build and run an app" in Android fundamentals 04.2 Please implement in @link{com.example.android.droidcafe.CheckboxesActivity}.

 * **See also:** <a href="https://codelabs.developers.google.com/codelabs/android-training-input-controls/index.html?index=..%2F..%2Fandroid-training#10">04.2: Input controls</a>

#### `@Test public void testCheckboxesLayout()`

04.2 [1] Create an app with five checkboxes and a Show Toast button.

#### `@Test public void testOnSubmitToast()`

04.2 [2] If the user selects a single checkbox and taps Show Toast, display a Toast message showing the checkbox that was selected. <br>
[3] If the user selects more than one checkbox and then taps Show Toast, display a Toast that includes the messages for all selected checkboxes, as shown in the figure below.


### `@RunWith(AndroidJUnit4.class) public class OrderActivityTest`

Tests for the Homework "Change an app" in Android fundamentals 04.3 Please implement in @link{com.example.android.droidcafe.OrderActivity}.

 * **See also:** <a href="https://codelabs.developers.google.com/codelabs/android-training-menus-and-pickers/index.html?index=..%2F..%2Fandroid-training#12">04.3: Menus and pickers</a>

#### `@Test public void testDateButton()`

04.3 [1] Add a Date button under the delivery options that shows the date picker.

#### `@Test public void testDatePickerToast()`

04.3 [2] Show the user's chosen date in a Toast message. Please format the toast message as "month/day/year"


License
-------

Copyright 2018 Google, Inc.

Licensed to the Apache Software Foundation (ASF) under one or more contributor
license agreements.  See the NOTICE file distributed with this work for
additional information regarding copyright ownership.  The ASF licenses this
file to you under the Apache License, Version 2.0 (the "License"); you may not
use this file except in compliance with the License.  You may obtain a copy of
the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
License for the specific language governing permissions and limitations under
the License.
