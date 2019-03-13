package com.example.android.hellotoast;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.PositionAssertions.isCompletelyAbove;
import static android.support.test.espresso.assertion.PositionAssertions.isCompletelyBelow;
import static android.support.test.espresso.assertion.PositionAssertions.isCompletelyLeftOf;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

/**
 * Tests for the 10.Homework "Change an app" in Android fundamentals 01.2 Part B: The layout editor
 *
 * @see <a href="https://codelabs.developers.google.com/codelabs/android-training-layout-editor-part-b/#9">01.2 Part B: The layout editor</a>
 */
@RunWith(AndroidJUnit4.class)
public class ChangeAnAppTest {

    private static final String ZERO_BUTTON = "ZERO";
    private static final String ZERO = "0";
    private static final String NON_ZERO = "1";

    private static ViewAction setTextInTextView(final String value) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return allOf(isDisplayed(), isAssignableFrom(TextView.class));
            }

            @Override
            public String getDescription() {
                return "replace text";
            }

            @Override
            public void perform(UiController uiController, View view) {
                ((TextView) view).setText(value);
            }
        };
    }

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    /**
     * [2]  the activity_main.xml layout to align the Toast and Count Button elements along the left side of the show_count TextView that shows "0".
     * [3]  Include a third Button called Zero that appears between the Toast and Count Button elements.
     */
    @Test
    public void testZeroButtonDisplay() {
        // Check that Toast Button is on the left side of the show_count TextView
        onView(withId(R.id.button_toast)).check(isCompletelyLeftOf(withId(R.id.show_count)));
        // Check that Count Button is on the left side of the show_count TextView
        onView(withId(R.id.button_count)).check(isCompletelyLeftOf(withId(R.id.show_count)));
        // Check that Zero Button is displayed
        onView(withText(equalToIgnoringCase(ZERO_BUTTON))).check(matches(isCompletelyDisplayed()));
        // Check that Zero Button on the bottom of the TOAST Button
        onView(withText(equalToIgnoringCase(ZERO_BUTTON))).check(isCompletelyBelow(withId(R.id.button_toast)));
        // Check that Zero Button on the top of the Count Button
        onView(withText(equalToIgnoringCase(ZERO_BUTTON))).check(isCompletelyAbove(withId(R.id.button_count)));

    }

    /**
     * [4] Distribute the Button elements vertically between the top and bottom of the show_count TextView.
     * [5] Set the Zero Button to initially have a gray background.
     *
     * @see <a href="https://developer.android.com/reference/android/graphics/Color">android Color</a>
     */
    @Test
    public void testZeroButtonLocation() {
        Activity currentActivity = mActivityRule.getActivity();
        View toastButton = currentActivity.findViewById(R.id.button_toast);
        View countButton = currentActivity.findViewById(R.id.button_count);
        View showTextView = currentActivity.findViewById(R.id.show_count);
        // Check that Toast Button is lower than the top of show_count TextView
        assertThat(toastButton.getTop(), greaterThanOrEqualTo(showTextView.getTop()));
        // Check that Count Button is higher than the bottom of of the show_count TextView
        assertThat(countButton.getBottom(), lessThanOrEqualTo(showTextView.getBottom()));
        // Check that Zero Button is initialized to a gray background
        onView(withText(equalToIgnoringCase(ZERO_BUTTON))).check(matches(new IsBackgroundColor(Color.GRAY)));
    }

    /**
     * [6] Make sure that you include the Zero Button for the landscape orientation in activity_main.xml (land),
     * (optional) and also for a tablet-sized screen in activity_main (xlarge).
     */
    @Test
    public void testZeroButtonInLayoutVariants() {
        // Check that Zero Button exists for landscape orientation
        mActivityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        onView(withText(equalToIgnoringCase(ZERO_BUTTON))).check(matches(isCompletelyDisplayed()));
    }

    /**
     * [7] Make the Zero Button change the value in the show_count TextView to 0.
     * [10] Update the click handler for the Zero Button to setZero the color to gray, so that it is gray when the count is zero.
     */
    @Test
    public void testZeroButtonFunction() {
        // Change the number in show_count TextView to a non zero number
        onView(withId(R.id.button_count)).perform(click());
        onView(withId(R.id.show_count)).perform(setTextInTextView(NON_ZERO));
        // Click Zero Button
        onView(withText(equalToIgnoringCase(ZERO_BUTTON))).perform(click());
        // Check that show_count TextVIew display 0, when Zero Button is clicked
        onView(withId(R.id.show_count)).check(matches(withText(ZERO)));
        // Check that the background color of Zero Button is gray
        onView(withText(equalToIgnoringCase(ZERO_BUTTON))).check(matches(new IsBackgroundColor(Color.GRAY)));
    }

    /**
     * [8] Update the click handler for the Count Button so that it changes its own background color, depending on whether the new count is odd or even.
     * [9] Update the click handler for the Count Button to set the background color for the Zero Button to something other than gray to show it is now active.
     */
    @Test
    public void testCountButtonFunction() {
        int currentNumber = 0;
        int colorForEven, colorForOdd;
        ColorDrawable countBackground = (ColorDrawable) mActivityRule.getActivity().findViewById(R.id.button_count).getBackground();

        // setZero the count number to zero
        onView(withId(R.id.show_count)).perform(setTextInTextView(Integer.toString(currentNumber)));
        // Record the color of Count Button when the count is even
        colorForEven = countBackground.getColor();

        // Click Count Button (from 0 to 1)
        onView(withId(R.id.button_count)).perform(click());
        currentNumber += 1;
        // Check whether Zero Button Change to some color other than Gray
        onView(withText(equalToIgnoringCase(ZERO_BUTTON))).check(matches(not(new IsBackgroundColor(Color.GRAY))));
        // Check whether the color of Count Button change to another color
        colorForOdd = countBackground.getColor();
        assertThat(colorForEven, is(not(equalTo(colorForOdd))));

        // Click Count Button (from 1 to 2)
        onView(withId(R.id.button_count)).perform(click());
        currentNumber += 1;
        // Check whether Zero Button Change to some color other than Gray
        onView(withText(equalToIgnoringCase(ZERO_BUTTON))).check(matches(not(new IsBackgroundColor(Color.GRAY))));
        // Check whether the color of Count Button change to the color for even count
        assertThat(colorForOddAndEven(currentNumber, countBackground, colorForOdd, colorForEven), is(true));

        // Click Count Button (from 2 to 3)
        onView(withId(R.id.button_count)).perform(click());
        currentNumber += 1;
        // Check whether Zero Button Change to some color other than Gray
        onView(withText(equalToIgnoringCase(ZERO_BUTTON))).check(matches(not(new IsBackgroundColor(Color.GRAY))));
        // Check whether the color of Count Button change to the color for odd count
        assertThat(colorForOddAndEven(currentNumber, countBackground, colorForOdd, colorForEven), is(true));

        // Click Zero Button (from 3 to 0)
        onView(withText(equalToIgnoringCase(ZERO_BUTTON))).perform(click());
        currentNumber = 0;
        // Check whether Zero Button Change to Gray
        onView(withText(equalToIgnoringCase(ZERO_BUTTON))).check(matches(new IsBackgroundColor(Color.GRAY)));

        // Click Count Button (from 0 to 1)
        onView(withId(R.id.button_count)).perform(click());
        currentNumber += 1;
        // Check whether Zero Button Change to some color other than Gray
        onView(withText(equalToIgnoringCase(ZERO_BUTTON))).check(matches(not(new IsBackgroundColor(Color.GRAY))));

        // Click Count Button (from 1 to 2)
        onView(withId(R.id.button_count)).perform(click());
        currentNumber += 1;
        // Check whether Zero Button Change to some color other than Gray
        onView(withText(equalToIgnoringCase(ZERO_BUTTON))).check(matches(not(new IsBackgroundColor(Color.GRAY))));
        // Check whether the color of Count Button change to the color for odd count
        assertThat(colorForOddAndEven(currentNumber, countBackground, colorForOdd, colorForEven), is(true));

        // Click Zero Button (from 2 to 0)
        onView(withText(equalToIgnoringCase(ZERO_BUTTON))).perform(click());
        currentNumber = 0;
        // Check whether Zero Button Change to Gray
        onView(withText(equalToIgnoringCase(ZERO_BUTTON))).check(matches(new IsBackgroundColor(Color.GRAY)));

    }

    private boolean colorForOddAndEven(int number, ColorDrawable backgraound, int colorForOdd, int colorForEven) {
        if (number % 2 == 0) {
            return backgraound.getColor() == colorForEven;
        } else {
            return backgraound.getColor() == colorForOdd;
        }
    }
}
