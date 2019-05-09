package com.example.android.simpleasynctask;

import android.support.annotation.CheckResult;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Tests for the 10.Homework "Build and run an app" in Android fundamentals 07.1: AsyncTask
 *
 * @see <a href="https://codelabs.developers.google.com/codelabs/android-training-create-asynctask/index.html?index=..%2F..%2Fandroid-training#9">07.1: AsyncTask</a>
 */
@RunWith(AndroidJUnit4.class)
public class ProgressBarTest {

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(MainActivity.class);

    /**
     * 07.1
     * Add a ProgressBar that displays the percentage of sleep time completed.
     * The progress bar fills up as the AsyncTask thread sleeps from a value of 0 to 100 (percent).
     */
    @Test
    public void testProgressBarLayout() {
        // The layout includes a ProgressBar (determinate)
        onView(Matchers.<View>instanceOf(ProgressBar.class)).check(matches(isDisplayed()));
        onView(Matchers.<View>instanceOf(ProgressBar.class)).check(matches(isDeterminate()));

        // The ProgressBar sets the appropriate attributes to determine the range of values.
        onView(Matchers.<View>instanceOf(ProgressBar.class)).check(matches(new withProgress("min")));
        onView(withId(R.id.button)).perform(click());
        onView(Matchers.<View>instanceOf(ProgressBar.class)).check(matches(new withProgress("max")));

    }

    public static Matcher<View> isDeterminate() {
        return new BoundedMatcher<View, ProgressBar>(ProgressBar.class) {

            @Override
            protected boolean matchesSafely(ProgressBar progressBar) {
                return !progressBar.isIndeterminate();
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("The progressBar is not determinate");
            }
        };
    }

    public final class withProgress extends BoundedMatcher<View, ProgressBar> {

        private String value;
        private int expectedProgress = 0, realProgress = 0;

        private withProgress(final String value) {
            super(ProgressBar.class);
            this.value = value;
        }

        @Override
        protected boolean matchesSafely(final ProgressBar progressBar) {

            switch (value) {
                case "max":
                    expectedProgress = progressBar.getMax();
                    break;
                case "min":
                    expectedProgress = progressBar.getMin();
                    break;
                default:
                    expectedProgress = new Integer(value);
                    break;
            }
            realProgress = progressBar.getProgress();
            return realProgress == expectedProgress;
        }

        @Override
        public void describeTo(final Description description) {
            description.appendText("has progress: ").appendValue(realProgress);
            description.appendText("expected progress: ").appendValue(expectedProgress);
        }
    }
}
