package com.example.android.droidcafe;

import android.content.pm.ActivityInfo;
import android.support.test.espresso.contrib.PickerActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.DatePicker;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Random;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.PositionAssertions.isCompletelyAbove;
import static android.support.test.espresso.assertion.PositionAssertions.isCompletelyLeftOf;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;

/**
 * Tests for the Homework "Change an app" in Android fundamentals 04.3
 * Please implement in @link{com.example.android.droidcafe.OrderActivity}.
 *
 * @see <a href="https://codelabs.developers.google.com/codelabs/android-training-menus-and-pickers/index.html?index=..%2F..%2Fandroid-training#12">04.3: Menus and pickers</a>
 */
@RunWith(AndroidJUnit4.class)
public class OrderActivityTest {

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(OrderActivity.class);

    /**
     * 04.3
     * [1] Add a Date button under the delivery options that shows the date picker.
     */
    @Test
    public void testDateButton() {
        // click date button
        onView(allOf(withText(equalToIgnoringCase("DATE")))).perform(click());
        // check whether datepicker displays
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).check(matches(isDisplayed()));
    }

    /**
     * 04.3
     * [2] Show the user's chosen date in a Toast message.
     * Please format the toast message as "month/day/year"
     */
    @Test
    public void testDatePickerToast() {
        // genreate random date
        Random random = new Random();
        int year = 2000 + random.nextInt(19);
        int month = 1 + random.nextInt(12);
        int day = 1 + random.nextInt(28);
        String dateString = Integer.toString(month) + "/" + Integer.toString(day) + "/" + Integer.toString(year);
        // set date
        onView(allOf(withText(equalToIgnoringCase("DATE")))).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(year, month, day));
        onView(withText("OK")).perform(click());
        // check whether the toast displays correct date message
        onView(withText(containsString(dateString))).inRoot(new CheckboxesActivityTest.ToastMatcher()).check(matches(isDisplayed()));

    }

}
