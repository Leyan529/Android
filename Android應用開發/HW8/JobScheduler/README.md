
JobSchedulerTest
---------------

### `@RunWith(AndroidJUnit4.class) public class JobServiceTest`

 Tests for the Homework "Build and run an app" in Android fundamentals 08.3: JobScheduler

 * **See also:** <a href="https://codelabs.developers.google.com/codelabs/android-training-job-scheduler/index.html?index=..%2F..%2Fandroid-training#9">08.3: JobScheduler</a>

### `@Test public void downloadNowButtonTest()`

08.3 Instead of performing an actual download, the app delivers a notification.When the user taps the Download Now button, it triggers a "downloading" notification.

---------------

### `@RunWith(RobolectricTestRunner.class) public class JobInfoTest`

 Tests for the Homework "Build and run an app" in Android fundamentals 08.3: JobScheduler

 * **See also:** <a href="https://codelabs.developers.google.com/codelabs/android-training-job-scheduler/index.html?index=..%2F..%2Fandroid-training#9">08.3: JobScheduler</a>

### `@Test public void testJobInfo()`
08.3 The "download" is performed once a day, when the phone is idle but connected to power and to Wi-Fi.
* **Define the JobService class as an inner class. That way, the Download Now button and the JobService can call the same method to deliver the notification.**

* service name : com.example.jobscheduler.NotificationJobService

