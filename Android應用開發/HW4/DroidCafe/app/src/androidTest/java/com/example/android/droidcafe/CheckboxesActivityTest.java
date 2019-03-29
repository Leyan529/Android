package com.example.android.droidcafe;

import android.content.pm.ActivityInfo;
import android.os.IBinder;
import android.support.test.espresso.Root;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.PositionAssertions.isCompletelyAbove;
import static android.support.test.espresso.assertion.PositionAssertions.isCompletelyLeftOf;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isNotChecked;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;

/**
 * Tests for the Homework "Build and run an app" in Android fundamentals 04.2
 * Please implement in @link{com.example.android.droidcafe.CheckboxesActivity}.
 * @see <a href="https://codelabs.developers.google.com/codelabs/android-training-input-controls/index.html?index=..%2F..%2Fandroid-training#10">04.2: Input controls</a>
 */
@RunWith(AndroidJUnit4.class)
public class CheckboxesActivityTest {

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(CheckboxesActivity.class);

    private final String BUTTON_TEXT = "SHOW TOAST";
    private final String[] checkboxList = {"Chocolate syrup", "Sprinkles", "Crushed nuts", "Cherries", "Orio cookie crumbles"};

    /**
     * 04.2
     * [1] Create an app with five checkboxes and a Show Toast button.
     */
    @Test
    public void testCheckboxesLayout() {

        // test checkboxes
        for (int i = 0; i < checkboxList.length; ++i) {
            onView(allOf(withText(checkboxList[i]), instanceOf(android.widget.CheckBox.class))).check(matches(isDisplayed()));
        }
        // test button
        onView(allOf(withText(equalToIgnoringCase(BUTTON_TEXT)), instanceOf(android.widget.Button.class))).check(matches(isDisplayed()));

    }

    /**
     * 04.2
     * [2] If the user selects a single checkbox and taps Show Toast, display a Toast message showing the checkbox that was selected.
     * [3] If the user selects more than one checkbox and then taps Show Toast, display a Toast that includes the messages for all selected checkboxes, as shown in the figure below.
     */
    @Test
    public void testOnSubmitToast() {
        // initialize variables
        Random random = new Random();
        String selected;

        // check all checkbox are unchecked at the beginning
        for (int i = 0; i < checkboxList.length; ++i) {
            onView(allOf(withText(checkboxList[i]), instanceOf(android.widget.CheckBox.class))).check(matches(isNotChecked()));
        }

        // generate 1 checked item randomly
        selected = checkboxList[random.nextInt(checkboxList.length)];
        onView(allOf(withText(selected), instanceOf(android.widget.CheckBox.class))).perform(click());
        onView(allOf(withText(equalToIgnoringCase(BUTTON_TEXT)), instanceOf(android.widget.Button.class))).perform(click());
        onView(withText(containsString(selected))).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
        // unchecked
        onView(allOf(withText(selected), instanceOf(android.widget.CheckBox.class))).perform(click());

        // generate multiple checked items randomly
        List<String> shuffleList = Arrays.asList(checkboxList);
        Collections.shuffle(shuffleList);
        shuffleList = shuffleList.subList(0, 2 + random.nextInt(checkboxList.length - 1));
        for (int i = 0; i < shuffleList.size(); ++i) {
            onView(allOf(withText(shuffleList.get(i)), instanceOf(android.widget.CheckBox.class))).perform(click());
        }
        onView(allOf(withText(equalToIgnoringCase(BUTTON_TEXT)), instanceOf(android.widget.Button.class))).perform(click());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withText(new withMultipleStrings(shuffleList))).inRoot(new ToastMatcher()).check(matches(isDisplayed()));

    }

    public static class ToastMatcher extends TypeSafeMatcher<Root> {

        @Override
        public void describeTo(Description description) {
            description.appendText("is toast");
        }

        @Override
        protected boolean matchesSafely(Root root) {
            int type = root.getWindowLayoutParams().get().type;
            if ((type == WindowManager.LayoutParams.TYPE_TOAST)) {
                IBinder windowToken = root.getDecorView().getWindowToken();
                IBinder appToken = root.getDecorView().getApplicationWindowToken();
                if (windowToken == appToken) {
                    return true;
                }
            }
            return false;
        }
    }

    public class withMultipleStrings extends TypeSafeMatcher {

        private List<String> list;

        protected withMultipleStrings(List<String> list) {
            this.list = list;
        }

        @Override
        protected boolean matchesSafely(Object item) {
            boolean result = true;
            for (int i = 0; i < list.size(); ++i) {
                result = result & (item.toString().indexOf(list.get(i)) >= 0);
            }
            return result;
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("The toast doesn't display all of the following strings: ");
            for (int i = 0; i < list.size(); ++i) {
                description.appendText(list.get(i) + " ");
            }
        }
    }

}
