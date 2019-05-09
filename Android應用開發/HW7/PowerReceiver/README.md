PowerReceiver Sample App (Solution Code)
========================================

The PowerReceiver app uses a Broadcast Receiver to receive the system Broadcast
Intent delivered when the device is connected and disconnected from power and
displays a Toast message. It also delivers a custom Broadcast Intent and responds
with another Toast message when the custom Broadcast is received.

## CustomBroadcastTest

### `@RunWith(AndroidJUnit4.class) public class CustomBroadcastTest`

Tests for the 10.Homework "Change an app" in Android fundamentals 07.3: Broadcast receivers

 * **See also:** <a href="https://codelabs.developers.google.com/codelabs/android-training-broadcast-receivers/index.html?index=..%2F..%2Fandroid-training#9">07.3: Broadcast receivers</a>

### `@Test public void testRangeOfRandomNumber()`

[1] Send extra data to your local custom broadcast receiver. To do this, generate a random integer between 1 and 20. Add the number to the extra field of the Intent before sending the local custom broadcast.

### `@Test public void testToastMessage()`

[2] In your receiver, extract the integer data from the Intent. In the toast message, display the square of the random number.


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
