package com.example.android.getwebsourcecode;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.is;

/**
 * Tests for the Homework "Build and run an app" in Android fundamentals 07.2
 * @see <a href="https://codelabs.developers.google.com/codelabs/android-training-asynctask-asynctaskloader/index.html?index=..%2F..%2Fandroid-training#10">07.2: AsyncTask and AsyncTaskLoader</a>
 */
@RunWith(AndroidJUnit4.class)
public class GetWebSourceCodeTest {

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(MainActivity.class);

    /**
     * 07.2 Create an app that retrieves and displays the contents of a web page that's located at a URL.
     *  The app displays the following:
     *  - A field in which the user enters a URL
     *  - field such as a menu or spinner that allows the user to choose the protocol (HTTP or HTTPS)
     *  - A button that executes the task when the user taps it
     *  - A scrolling display of the source code of the web page at the URL
     */
    @Test
    public void testWebSourceCodeLayout() {
        // A field in which the user enters a URL
        onView(Matchers.<View>instanceOf(EditText.class)).check(matches(isDisplayed()));
        // A spinner that allows the user to choose the protocol (HTTP or HTTPS)
        onView(Matchers.<View>instanceOf(Spinner.class)).perform(click());
        onData(is("https://")).perform(click());
        onView(withSpinnerText("https://")).check(matches(isDisplayed()));
        onView(Matchers.<View>instanceOf(Spinner.class)).perform(click());
        onData(is("http://")).perform(click());
        onView(withSpinnerText("http://")).check(matches(isDisplayed()));
        // A button that executes the task when the user taps it (with text "GET PAGE SOURCE")
        onView(allOf(withText(equalToIgnoringCase("GET PAGE SOURCE")), Matchers.<View>instanceOf(android.widget.Button.class))).check(matches(isDisplayed()));
        // click button
        onView(allOf(withText(equalToIgnoringCase("GET PAGE SOURCE")), Matchers.<View>instanceOf(android.widget.Button.class))).perform(click());
        // A scrolling display of the source code of the web page at the URL
        onView(allOf(Matchers.<View>instanceOf(TextView.class), isDescendantOfA(Matchers.<View>instanceOf(ScrollView.class)))).check(matches(isDisplayed()));
    }

    /**
     * 07.2 The display must contain a TextView in a ScrollView that displays the source code. (http and https)
     */
    @Test
    public void testWebSourceCodeFunction() {

        Random random = new Random();
        String baseUrl = "aad-hw7-http-server.herokuapp.com/";
        String proto;
        String randomNumber;

        // ====http test====
        proto="http";
        randomNumber=Integer.toString(random.nextInt(10));

        // choose protocol
        onView(Matchers.<View>instanceOf(Spinner.class)).perform(click());
        onData(is("http://")).perform(click());
        // input URL
        onView(Matchers.<View>instanceOf(EditText.class)).perform(clearText());
        onView(Matchers.<View>instanceOf(EditText.class)).perform(typeText(baseUrl+randomNumber));
        // click get page source
        onView(allOf(withText(equalToIgnoringCase("GET PAGE SOURCE")), Matchers.<View>instanceOf(android.widget.Button.class))).perform(click());
        // check the result
        onView(allOf(Matchers.<View>instanceOf(TextView.class), isDescendantOfA(Matchers.<View>instanceOf(ScrollView.class)))).check(matches(withText(containsString(proto+" "+randomNumber))));

        // ====https test====
        proto="https";
        randomNumber=Integer.toString(random.nextInt(10));

        // choose protocol
        onView(Matchers.<View>instanceOf(Spinner.class)).perform(click());
        onData(is("https://")).perform(click());
        // input URL
        onView(Matchers.<View>instanceOf(EditText.class)).perform(clearText());
        onView(Matchers.<View>instanceOf(EditText.class)).perform(typeText(baseUrl+randomNumber));
        // click get page source
        onView(allOf(withText(equalToIgnoringCase("GET PAGE SOURCE")), Matchers.<View>instanceOf(android.widget.Button.class))).perform(click());
        // check the result
        onView(allOf(Matchers.<View>instanceOf(TextView.class), isDescendantOfA(Matchers.<View>instanceOf(ScrollView.class)))).check(matches(withText(containsString(proto+" "+randomNumber))));
    }

}
