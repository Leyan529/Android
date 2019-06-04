package com.example.jobscheduler;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.DisplayMetrics;

import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.Until;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.*;

/**
 * Tests for the Homework "Build and run an app" in Android fundamentals 08.3: JobScheduler
 * @see <a href="https://codelabs.developers.google.com/codelabs/android-training-job-scheduler/index.html?index=..%2F..%2Fandroid-training#9">08.3: JobScheduler</a>
 */
@RunWith(AndroidJUnit4.class)
public class JobServiceTest {
    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(MainActivity.class);

    final String NOTIFICATION_TITLE = "Performing Work";
    final String NOTIFICATION_TEXT = "Download in progress";
    final long TIMEOUT = 5000;

    /**
     * [1]  Instead of performing an actual download, the app delivers a notification.When the user taps the Download Now button, it triggers a "downloading" notification.
     */

    @Test
    public void  downloadNowButtonTest() {
        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        
        onView(allOf(is(instanceOf(android.widget.Button.class)),withText(equalToIgnoringCase("Download Now"))))
                .perform(click());

        device.openNotification();
        device.wait(Until.hasObject(By.text(NOTIFICATION_TITLE)), TIMEOUT);
        UiObject2 title = device.findObject(By.text(NOTIFICATION_TITLE));
        assertEquals(NOTIFICATION_TITLE, title.getText());
        UiObject2 text1 = device.findObject(By.text(NOTIFICATION_TEXT));
        assertEquals(NOTIFICATION_TEXT, text1.getText());
    }
}
