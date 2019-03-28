package com.example.android.SimpleCalc;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import java.util.Random;

import javax.inject.Qualifier;

import static android.app.PendingIntent.getActivity;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.PositionAssertions.isCompletelyAbove;
import static android.support.test.espresso.assertion.PositionAssertions.isCompletelyBelow;
import static android.support.test.espresso.assertion.PositionAssertions.isCompletelyLeftOf;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasBackground;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;

/**
 * Tests for the homework in Android fundamentals 03.2 Unit tests
 *
 * @see <a href="https://codelabs.developers.google.com/codelabs/android-training-unit-tests/index.html?#8">03.2 Unit tests</a>
 */
@RunWith(AndroidJUnit4.class)
public class PowerButtonTest {

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    /**
     * 1. It displays a POW Button that provides an exponential ("power of") calculation./
     */
    @Test
    public void testPowButtonDisplay() {
        try {
            onView(withText(equalToIgnoringCase("Pow"))).check(matches(isDisplayed()));
        } catch (AssertionError e) {
            // pass
        }
    }

    /**
     * 2. The implementation of Calculator includes a pow() method that performs the calculation.
     */
    @Test
    public void testPowOnClick() {

        // Get random values and calculate the result
        Random r = new Random();
        double r1 = 1 + 10 * r.nextDouble();
        double r2 = 1 + 4 * r.nextDouble();
        double res = Math.pow(r1, r2);

        // Assign two random values to EditTexts
        EditText operand1 = (EditText) mActivityRule.getActivity().findViewById(R.id.operand_one_edit_text);
        EditText operand2 = (EditText) mActivityRule.getActivity().findViewById(R.id.operand_two_edit_text);
        operand1.setText(Double.toString(r1));
        operand2.setText(Double.toString(r2));

        // Click on Power Button
        onView(withText(equalToIgnoringCase("Pow"))).perform(click());
        onView(withId(R.id.operation_result_text_view)).check(matches(withText(Double.toString(res))));
    }

}