SimpleAsyncTask Sample App (Solution Code)
============

The SimpleAsyncTask app uses an AsyncTask that simulates doing work on a
background thread by putting that thread to sleep for a random amount of
time, and then displaying that time in the UI.

## ProgressBarTest

### `@RunWith(AndroidJUnit4.class) public class ProgressBarTest`

Tests for the 10.Homework "Build and run an app" in Android fundamentals 07.1: AsyncTask

 * **See also:** <a href="https://codelabs.developers.google.com/codelabs/android-training-create-asynctask/index.html?index=..%2F..%2Fandroid-training#9">07.1: AsyncTask</a>

### `@Test public void testProgressBarLayout()`

07.1 Add a ProgressBar that displays the percentage of sleep time completed. The progress bar fills up as the AsyncTask thread sleeps from a value of 0 to 100 (percent).


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
