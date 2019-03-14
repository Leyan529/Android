package com.example.android.hellotoast;


import android.app.Activity;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.TextView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

/*  Tests for the 12.Homework "Build and run an app" in Android fundamentals 02.1: Activities and intents
 *
 * @see <a href="https://codelabs.developers.google.com/codelabs/android-training-create-an-activity/index.html?index=..%2F..%2Fandroid-training#11">02.1: Activities and intents</a>
 */
@RunWith(AndroidJUnit4.class)
public class IntentTest {

    @Rule
    public IntentsTestRule<MainActivity> mIntentsRule = new IntentsTestRule<>(MainActivity.class);

    /**
     * [1]  Modify the Toast button so that it launches a new Activity to display the word "Hello!" and the current count.
     */
    @Test
    public void newActivityLaunchTest() {
        int currentNumber = 0;
        Random ran = new Random();
        int random=ran.nextInt(10)+1;

        onView(withId(R.id.show_count)).check(matches(withText(Integer.toString(currentNumber))));
        for(int i=0;i<random;i++) {
            onView(withId(R.id.button_count)).perform(click());
            currentNumber += 1;
        }
        onView(withId(R.id.show_count)).check(matches(withText(Integer.toString(currentNumber))));

        onView(withId(R.id.button_toast)).perform(click());
        mIntentsRule.getActivity();
        onView(allOf(is(instanceOf(android.widget.TextView.class)),withText("Hello!")))
                .check(matches(isDisplayed()));
        onView(allOf(is(instanceOf(android.widget.TextView.class)),withText(Integer.toString(currentNumber))))
                .check(matches(isDisplayed()));
    }


    /**
     * [2]  Change the text on the Toast button to Say Hello.
     */
    @Test
    public void buttonTextTest() {
        // Check that Toast button text change to Say Hello.
        onView(withId(R.id.button_toast)).check(matches(withText("Say Hello")));
    }
}
