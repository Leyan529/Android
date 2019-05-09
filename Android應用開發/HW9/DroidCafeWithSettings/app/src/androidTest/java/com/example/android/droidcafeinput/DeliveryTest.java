package com.example.android.droidcafeinput;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.AppCompatCheckedTextView;
import android.view.View;
import android.widget.TextView;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.hasChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.hasSibling;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class DeliveryTest {

    static private int SLEEP_INTERVAL = 500;
    static private String optString = "";

    static private boolean clearFlag = false;

    static Matcher<View> titleMatcher = allOf(withText(anyOf(
            equalToIgnoringCase("Choose a delivery method"),
            equalToIgnoringCase("Choose a delivery method:")
    )), Matchers.<View>instanceOf(TextView.class));
    static String opt1 = "Same day messenger service";
    static String opt2 = "Next day ground delivery";
    static String opt3 = "Pick up";

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<MainActivity>(MainActivity.class) {
                @Override
                protected void beforeActivityLaunched() {
                    if (clearFlag) {
                        clearSharedPrefs(InstrumentationRegistry.getTargetContext());
                    }
                    super.beforeActivityLaunched();
                }
            };

    /**
     * Clears everything in the SharedPreferences
     */
    private void clearSharedPrefs(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.commit();
    }

    /**
     * 1. Add a ListPreference (a dialog with radio buttons) to the general settings. Put the dialog in the General settings screen, below the "Add friends to order messages" ListPreference.
     * XXX: Can we ensure students implement the function with ListPreference?
     */
    @Test
    public void testDeliveryPrefsInGeneral() {
        openActionBarOverflowOrOptionsMenu(mActivityRule.getActivity());
        onView(withText(equalToIgnoringCase("Settings"))).perform(click());
        onView(withText(equalToIgnoringCase("General"))).perform(click());

        onView(titleMatcher).check(matches(isDisplayed()));
    }

    /**
     * 2. Edit the string arrays used for the ListPreference to include the title "Choose a delivery method." Use the same delivery choices that are used in the radio buttons in the OrderActivity.
     */
    @Test
    public void testDeliveryPrefsContent() {
        // click
        openActionBarOverflowOrOptionsMenu(mActivityRule.getActivity());
        onView(withText(equalToIgnoringCase("Settings"))).perform(click());
        onView(withText(equalToIgnoringCase("General"))).perform(click());
        onData(anything()).inAdapterView(hasChildCount(4)).atPosition(3).perform(click());

        // check all delivery options are displayed
        onView(withText(equalToIgnoringCase(opt1))).check(matches(isDisplayed()));
        onView(withText(equalToIgnoringCase(opt2))).check(matches(isDisplayed()));
        onView(withText(equalToIgnoringCase(opt3))).check(matches(isDisplayed()));
    }

    public void randomClickOptionSaveResult() {
        // click
        openActionBarOverflowOrOptionsMenu(mActivityRule.getActivity());
        onView(withText(equalToIgnoringCase("Settings"))).perform(click());
        onView(withText(equalToIgnoringCase("General"))).perform(click());
        onData(anything()).inAdapterView(hasChildCount(4)).atPosition(3).perform(click());

        // random click an option and save the result
        int r = (int) (Math.random()*3);
        onData(anything()).inAdapterView(hasChildCount(3)).atPosition(r).perform(new saveOptString());
        onData(anything()).inAdapterView(hasChildCount(3)).atPosition(r).perform(click());
    }

    /**
     * 3. Make the user's chosen delivery method appear in the same Toast message as the chosen Market setting.
     */
    @Test
    public void testToastWithDeliveryPrefs() {

        randomClickOptionSaveResult();

        // go back to the main activity; otherwise, we can not finishActivity()
        pressBack();
        pressBack();
        // restart app
        mActivityRule.finishActivity();
        mActivityRule.launchActivity(null);

        // wait for toast showing
        try {
            Thread.sleep(SLEEP_INTERVAL);
        }
        catch (Exception e) {
            // do nothing
        }
        // check content of toast
        onView(withText(containsString(optString))).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    /**
     * 4. Extra credit: Show the selected delivery method as the setting summary text that appears underneath the ListPreference title. Enable this text to change with each update.
     */
    @Test
    public void testUpdateSummary() {

        randomClickOptionSaveResult();

        // check the summary, a sibling of title, is updated correctly.
        onView(allOf(withText(optString), hasSibling(titleMatcher))).check(matches(isDisplayed()));
    }

    public class saveOptString implements ViewAction {
        @Override
        public Matcher<View> getConstraints() {
            return allOf(isDisplayed(), isAssignableFrom(AppCompatCheckedTextView.class));
        }

        @Override
        public void perform(UiController uiController, View view) {
            optString = (String) ((AppCompatCheckedTextView) view).getText();
        }

        @Override
        public String getDescription() {
            return "Save the option string selected randomly.";
        }
    }

}