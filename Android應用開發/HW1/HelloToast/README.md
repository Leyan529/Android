HelloToast
==========================
This is the Starter Code for 01.2 Part B Homework.

Introduction
----------
Simple app with two buttons: one to count and one to show a Toast.

Test Cases
--------

#### `@RunWith(AndroidJUnit4.class) public class ChangeAnAppTest`

Tests for the 10.Homework "Change an app" in Android fundamentals 01.2 Part B: The layout editor

 * **See also:** <a href="https://codelabs.developers.google.com/codelabs/android-training-layout-editor-part-b/#9">01.2 Part B: The layout editor</a>

#### `@Test public void testZeroButtonDisplay()`

[2] the activity_main.xml layout to align the Toast and Count Button elements along the left side of the show_count TextView that shows "0".  \
[3] Include a third Button called Zero that appears between the Toast and Count Button elements.

#### `@Test public void testZeroButtonLocation()`

[4] Distribute the Button elements vertically between the top and bottom of the show_count TextView. \
[5] Set the Zero Button to initially have a gray background. (Color.GRAY)

 * **See also:** <a href="https://developer.android.com/reference/android/graphics/Color">android Color</a>

#### `@Test public void testZeroButtonInLayoutVariants()`

[6] Make sure that you include the Zero Button for the landscape orientation in activity_main.xml (land), (optional) and also for a tablet-sized screen in activity_main (xlarge).

#### `@Test public void testZeroButtonFunction()`

[7] Make the Zero Button change the value in the show_count TextView to 0. \
[10] Update the click handler for the Zero Button to setZero the color to gray, so that it is gray when the count is zero.

#### `@Test public void testCountButtonFunction()`

[8] Update the click handler for the Count Button so that it changes its own background color, depending on whether the new count is odd or even. \
[9] Update the click handler for the Count Button to set the background color for the Zero Button to something other than gray to show it is now active.


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
