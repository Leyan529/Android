package com.example.android.droidcafe;

import android.content.pm.ActivityInfo;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.PositionAssertions.isCompletelyAbove;
import static android.support.test.espresso.assertion.PositionAssertions.isCompletelyLeftOf;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;

/**
 * Tests for the Homework "Change an app" in Android fundamentals 04.1
 * Please implement in @link{com.example.android.droidcafe.MainActivity}.
 * @see <a href="https://codelabs.developers.google.com/codelabs/android-training-clickable-images/index.html?index=..%2F..%2Fandroid-training#9">04.1: Clickable images</a>
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(MainActivity.class);

    /**
     * 04.1
     * [2] Create a layout variant for horizontal orientation: content_main.xml (land).
     * [3] Remove constraints from the three images and three text descriptions.
     * [4] Select all three images in the layout variant, and choose Expand Horizontally in the Pack button  to evenly distribute the images across the screen as shown in the figure below.
     * [5]Constrain the text descriptions to the sides and bottoms of the images as shown in the figure below.
     */
    @Test
    public void testHorizontalLayout() {
        // change orientation
        mActivityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        // test the relative location of images (horizontal)
        onView(withId(R.id.donut)).check(isCompletelyLeftOf(withId(R.id.ice_cream)));
        onView(withId(R.id.ice_cream)).check(isCompletelyLeftOf(withId(R.id.froyo)));
        //test the relative location of description text (horizontal)
        onView(withId(R.id.donut_description)).check(isCompletelyLeftOf(withId(R.id.ice_cream_description)));
        onView(withId(R.id.ice_cream_description)).check(isCompletelyLeftOf(withId(R.id.froyo_description)));
        //test the relative location of images and description text (vertical)
        onView(withId(R.id.donut)).check(isCompletelyAbove(withId(R.id.donut_description)));
        onView(withId(R.id.ice_cream)).check(isCompletelyAbove(withId(R.id.ice_cream_description)));
        onView(withId(R.id.froyo)).check(isCompletelyAbove(withId(R.id.froyo_description)));
    }

}
