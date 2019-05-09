package com.example.android.getwebsourcecode;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.is;

/**
 * Tests for the Homework "Build and run an app" in Android fundamentals 07.2
 *
 * @see <a href="https://codelabs.developers.google.com/codelabs/android-training-asynctask-asynctaskloader/index.html?index=..%2F..%2Fandroid-training#10">07.2: AsyncTask and AsyncTaskLoader</a>
 */
@RunWith(AndroidJUnit4.class)

public class NoInternetTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    /**
     * 07.2 If connection to the internet is not available when the user taps the button, the app must show the user an appropriate response.
     * For example, the app might display a message such as "Check your internet connection and try again."
     */
    @Test
    public void testNoInternetResponse() {
        // Responds appropriately if the device can't connect to the internet.
        String baseUrl = "aad-hw7-http-server.herokuapp.com/";

        // choose protocol
        onView(Matchers.<View>instanceOf(Spinner.class)).perform(click());
        onData(is("http://")).perform(click());
        // input URL
        onView(Matchers.<View>instanceOf(EditText.class)).perform(clearText());
        onView(Matchers.<View>instanceOf(EditText.class)).perform(typeText(baseUrl));
        // click get page source
        onView(allOf(withText(equalToIgnoringCase("GET PAGE SOURCE")), Matchers.<View>instanceOf(android.widget.Button.class))).perform(click());
        // check the result
        onView(allOf(Matchers.<View>instanceOf(TextView.class), isDescendantOfA(Matchers.<View>instanceOf(ScrollView.class)))).check(matches(anyOf(withText(new containsStringIgnoringCase("internet")), withText(new containsStringIgnoringCase("connection")), withText(new containsStringIgnoringCase("network")))));
    }

    public class containsStringIgnoringCase extends TypeSafeMatcher {

        protected final String substring;

        protected containsStringIgnoringCase(String substring) {
            this.substring = substring.toLowerCase();
        }

        @Override
        public boolean matchesSafely(Object item) {
            return item.toString().toLowerCase().contains(substring);
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("a string ")
                    .appendText("containing")
                    .appendText(" ")
                    .appendValue(substring)
                    .appendText(" ignoring case");
        }
    }
}
