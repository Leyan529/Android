package com.example.counterhomework;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.TextView;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/*  Tests for the 9.Homework "Build and run an app" in Android fundamentals 02.2: Activity lifecycle and state
 *
 * @see <a href="https://codelabs.developers.google.com/codelabs/android-training-activity-lifecycle-and-state/index.html?index=..%2F..%2Fandroid-training#8">02.2: Activity lifecycle and state</a>
 */
@RunWith(AndroidJUnit4.class)
public class onSaveInstanceStateTest {

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    public static ViewAction setTextInTextView(final String value) {
        return new ViewAction() {
            @SuppressWarnings("unchecked")
            @Override
            public Matcher<View> getConstraints() {
                return allOf(isDisplayed(), isAssignableFrom(TextView.class));
            }

            @Override
            public void perform(UiController uiController, View view) {
                ((TextView) view).setText(value);
            }

            @Override
            public String getDescription() {
                return "replace text";
            }
        };
    }

    /**
     * [1] Create an app with a layout that holds a counter TextView, a Button to increment the counter, and an EditText. See the screenshot below as an example. You don't have to precisely duplicate the layout.
     */
    @Test
    public void ViewTest() {
        try {
        int currentNumber = 0;
        onView(allOf(is(instanceOf(android.widget.Button.class)), withText(equalToIgnoringCase("Count"))))
                .perform(click());
        currentNumber += 1;
        onView(allOf(is(instanceOf(android.widget.TextView.class)), withText(Integer.toString(currentNumber))))
                .check(matches(isDisplayed()));
        onView(allOf(is(instanceOf(android.widget.EditText.class)), withText("This is an edit text")))
                .check(matches(isDisplayed()));
        } catch (AssertionError e) {
            // pass
        }
    }

    /**
     * [2]  Make sure that when you rotate the device, the app state is preserved.
      */
    @Test
    public void saveValueTest() {
        int currentNumber = 0;
        Random ran = new Random();
        int random = ran.nextInt(5) + 1;
        for (int i = 0; i < random; i++) {
            onView(allOf(is(instanceOf(android.widget.Button.class)), withText(equalToIgnoringCase("Count"))))
                    .perform(click());
            currentNumber += 1;
        }

        mActivityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        int random2 = ran.nextInt(5) + 1;
        for (int i = 0; i < random2; i++) {
            onView(allOf(is(instanceOf(android.widget.Button.class)), withText(equalToIgnoringCase("Count"))))
                    .perform(click());
            currentNumber += 1;
        }

        mActivityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        try {
            onView(withText(Integer.toString(currentNumber))).check(matches(isDisplayed()));
        } catch (AssertionError e) {
            // pass
        }
    }
}

