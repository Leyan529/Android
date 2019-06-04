package com.android.example.notifyme;

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
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;

/**
 * Tests for the Homework "Build and run an app" in Android fundamentals 08.1: Notifications
 * @see <a href="https://codelabs.developers.google.com/codelabs/android-training-notifications/index.html?index=..%2F..%2Fandroid-training#10">08.1: Notifications</a>
 */
@RunWith(AndroidJUnit4.class)
public class InboxStyleTest {
    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(MainActivity.class);

    final String NOTIFICATION_TITLE = "Title";
    final String NOTIFICATION_SUMMARY = "+1 more";
    final String NOTIFICATION_TEXT1 = "Here is the first one";
    final String NOTIFICATION_TEXT2 = "This is the second one";
    final String NOTIFICATION_TEXT3 = "Yay last one";
    final long TIMEOUT = 5000;

    /**
     * [1]  Change the updated notification in the app to use the InboxStyle expanded layout instead of BigPictureStyle.
     *        Use fake string data for each line, and for the summary text.
     */

    @Test
    public void notificationTest() {
        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        onView(withId(R.id.notify)).perform(click());
        onView(withId(R.id.update)).perform(click());

        device.openNotification();
        device.wait(Until.hasObject(By.text(NOTIFICATION_TITLE)), TIMEOUT);
        UiObject2 title = device.findObject(By.text(NOTIFICATION_TITLE));
        assertEquals(NOTIFICATION_TITLE, title.getText());
        UiObject2 summary_text = device.findObject(By.text(NOTIFICATION_SUMMARY));
        assertEquals(NOTIFICATION_SUMMARY, summary_text.getText());
        title.click();
        UiObject2 text1 = device.findObject(By.text(NOTIFICATION_TEXT1));
        assertEquals(NOTIFICATION_TEXT1, text1.getText());
        UiObject2 text2 = device.findObject(By.text(NOTIFICATION_TEXT2));
        assertEquals(NOTIFICATION_TEXT2, text2.getText());
        UiObject2 text3 = device.findObject(By.text(NOTIFICATION_TEXT3));
        assertEquals(NOTIFICATION_TEXT3, text3.getText());
    }
}
