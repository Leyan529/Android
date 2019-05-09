package com.android.fundamentals.powerreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.test.espresso.Root;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.runner.intercepting.SingleActivityFactory;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.WindowManager;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashSet;
import java.util.Random;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.getIdlingResources;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.anyIntent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

/**
 * Tests for the 10.Homework "Change an app" in Android fundamentals 07.3: Broadcast receivers
 *
 * @see <a href="https://codelabs.developers.google.com/codelabs/android-training-broadcast-receivers/index.html?index=..%2F..%2Fandroid-training#9">07.3: Broadcast receivers</a>
 */
@RunWith(AndroidJUnit4.class)
public class CustomBroadcastTest {

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(MainActivity.class);

    private Intent broadcastIntent = null;

    private static final String ACTION_CUSTOM_BROADCAST =
            BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";

    @Before
    public void init() {
        // initialize BroadcastReceiver
        MyReceiver receiver = new MyReceiver();
        LocalBroadcastManager.getInstance(mActivityRule.getActivity()).registerReceiver(receiver, new IntentFilter(ACTION_CUSTOM_BROADCAST));
    }

    /**
     * [1] Send extra data to your local custom broadcast receiver.
     * To do this, generate a random integer between 1 and 20.
     * Add the number to the extra field of the Intent before sending the local custom broadcast.
     */
    @Test
    public void testRangeOfRandomNumber() {
        // test for multiple times (5~10) to check whether implement random number
        // select random repeating times
        Random random = new Random();
        int repeatedTime = 5 + random.nextInt(5);
        HashSet<Integer> hash = new HashSet<Integer>();
        // repeat
        for (int i = 0; i < repeatedTime; ++i) {
            // click button
            onView(withId(R.id.sendBroadcast)).perform(click());
            // check broadcastIntent
            assertThat(broadcastIntent, hasAction(ACTION_CUSTOM_BROADCAST));
            // get and check random number
            for (String keys : broadcastIntent.getExtras().keySet()) {
                int randomNumber = new Integer(String.valueOf(broadcastIntent.getExtras().get(keys)));
                hash.add(randomNumber);
                assertThat(randomNumber, greaterThanOrEqualTo(1));
                assertThat(randomNumber, lessThanOrEqualTo(20));
            }
        }
        // check the size of number set
        assertThat(hash.size(), greaterThan(1));
    }

    /**
     * [2] In your receiver, extract the integer data from the Intent.
     * In the toast message, display the square of the random number.
     */
    @Test
    public void testToastMessage() {
        // click button and get random number
        onView(withId(R.id.sendBroadcast)).perform(click());
        assertThat(broadcastIntent, hasAction(ACTION_CUSTOM_BROADCAST));
        int randomNumber = 1;
        for (String keys : broadcastIntent.getExtras().keySet()) {
            randomNumber = new Integer(String.valueOf(broadcastIntent.getExtras().get(keys)));
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // check whether the toast display the square of random number
        onView(withText(containsString(Integer.toString(randomNumber * randomNumber)))).inRoot(new CustomBroadcastTest.ToastMatcher()).check(matches(isDisplayed()));
    }

    public class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            broadcastIntent = intent;
        }
    }

    public static class ToastMatcher extends TypeSafeMatcher<Root> {

        @Override
        public void describeTo(Description description) {
            description.appendText("is toast");
        }

        @Override
        protected boolean matchesSafely(Root root) {
            int type = root.getWindowLayoutParams().get().type;
            if ((type == WindowManager.LayoutParams.TYPE_TOAST)) {
                IBinder windowToken = root.getDecorView().getWindowToken();
                IBinder appToken = root.getDecorView().getApplicationWindowToken();
                if (windowToken == appToken) {
                    return true;
                }
            }
            return false;
        }
    }
}
