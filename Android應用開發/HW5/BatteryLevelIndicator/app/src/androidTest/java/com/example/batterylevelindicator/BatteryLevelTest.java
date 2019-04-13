package com.example.batterylevelindicator;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.ImageView;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

/*  Tests for the 12.Homework "Build and run an app" in Android fundamentals 05.1: Drawables, styles, and themes
 *
 * @see <a href="https://codelabs.developers.google.com/codelabs/android-training-drawables-styles-and-themes/index.html?index=..%2F..%2Fandroid-training#11">05.1: Drawables, styles, and themes</a>
 */
@RunWith(AndroidJUnit4.class)
public class BatteryLevelTest{

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(MainActivity.class);

    /**
     * [1] Create an app that displays an ImageView and plus(+) and minus(-) buttons.
     */
    @Test
    public void viewTest() {
        onView(allOf(is(instanceOf(android.widget.Button.class)), withText(equalToIgnoringCase("+"))))
                .check(matches(isDisplayed()));
        onView(allOf(is(instanceOf(android.widget.Button.class)), withText(equalToIgnoringCase("-"))))
                .check(matches(isDisplayed()));
        onView(Matchers.<View>instanceOf(ImageView.class))
                .check(matches(isDisplayed()));
    }
}
