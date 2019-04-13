package com.example.android.recyclerview;

import android.content.pm.ActivityInfo;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.CoordinatesProvider;
import android.support.test.espresso.action.GeneralClickAction;
import android.support.test.espresso.action.Press;
import android.support.test.espresso.action.Tap;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.DisplayMetrics;
import android.view.View;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class GridLayoutManagerTest {
    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(MainActivity.class);

    /**
     * [1] For a phone: 1 column in portrait orientation, 2 columns in landscape orientation
     */
    @Test
    public void GridLayoutManagerTest() {
        DisplayMetrics metrics = new DisplayMetrics();
        mActivityRule.getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        int height= metrics.heightPixels;

        onView(withId(R.id.recyclerview)).perform(clickXY(10, 10));
        onView(withText("Clicked! Word 0")).check(matches(isDisplayed()));
        onView(withId(R.id.recyclerview)).perform(clickXY(width/2, 10));
        onView(withText("Clicked! Clicked! Word 0")).check(matches(isDisplayed()));
        
        mActivityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        onView(withId(R.id.recyclerview)).perform(clickXY(10, 10));
        onView(withText("Clicked! Word 0")).check(matches(isDisplayed()));
        onView(withId(R.id.recyclerview)).perform(clickXY(height/2, 10));
        onView(withText("Clicked! Word 1")).check(matches(isDisplayed()));
    }

    public static ViewAction clickXY(final int x, final int y){
        return new GeneralClickAction(
                Tap.SINGLE,
                new CoordinatesProvider() {
                    @Override
                    public float[] calculateCoordinates(View view) {

                        final int[] screenPos = new int[2];
                        view.getLocationOnScreen(screenPos);

                        final float screenX = screenPos[0] + x;
                        final float screenY = screenPos[1] + y;
                        float[] coordinates = {screenX, screenY};

                        return coordinates;
                    }
                },
                Press.FINGER);
    }
}
