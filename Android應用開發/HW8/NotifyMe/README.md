Notify Me! Sample App (Solution Code)
=====================================

The Notify Me! app delivers a notification in response to a button click.
The notification has the ability to update itself with a picture as well as
cancel itself using notification actions.

Pre-requisites
--------------

For this app you should be familiar with:

* Creating and running apps in Android Studio.
* Receiving Broadcast Intents in a Broadcast Receiver.
* Creating a Notification with the Notification Builder.
* Updating and Canceling Notifications.
* Using PendingIntents to respond to Notification Actions.

Getting Started
---------------

1. Download and open the app in Android Studio.


NotifyMeTest
---------------

### `@RunWith(AndroidJUnit4.class) public class InboxStyleTest`

Tests for the Homework "Build and run an app" in Android fundamentals 08.1: Notifications

 * **See also:** <a href="https://codelabs.developers.google.com/codelabs/android-training-notifications/index.html?index=..%2F..%2Fandroid-training#10">08.1: Notifications</a>
 
### `@Test public void notificationTest()`

08.1 Open the solution code for the NotifyMe app. Change the updated notification in the app to use the InboxStyle expanded layout instead of BigPictureStyle. Use fake string data for each line, and for the summary text.
* title text : Title
* first line text : Here is the first one
* second line text : This is the second one
* third line text : Yay last one
* summary text : +1 more

License
-------

Copyright 2016 Google, Inc.

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
