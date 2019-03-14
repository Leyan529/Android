package com.example.android.implicitintents;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.PositionAssertions.isBelow;
import static android.support.test.espresso.assertion.PositionAssertions.isBottomAlignedWith;
import static android.support.test.espresso.assertion.PositionAssertions.isCompletelyAbove;
import static android.support.test.espresso.assertion.PositionAssertions.isCompletelyBelow;
import static android.support.test.espresso.assertion.PositionAssertions.isCompletelyLeftOf;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;


/*  Tests for the 12.Homework "Build and run an app" in Android fundamentals 02.3: Implicit intents
 *
 * @see <a href="https://codelabs.developers.google.com/codelabs/android-training-activity-with-implicit-intent/index.html?index=..%2F..%2Fandroid-training#11">02.3: Implicit intents</a>
 */
@RunWith(AndroidJUnit4.class)
public class TakePictureTest {

    @Rule
    public IntentsTestRule mIntentsRule = new IntentsTestRule<>(MainActivity.class);

    /**
     * [1]It displays a Take a Picture button at the bottom of the app.
     */
    @Test
    public void testTakeaPictureButtonLocation(){
        // Check Take a Picture button at the bottom of the screen
        onView(allOf(is(instanceOf(android.widget.Button.class)),withText(equalToIgnoringCase("Take a Picture"))))
                       .check(isCompletelyBelow(withId(R.id.share_text_button)));
    }

    /**
     * [2]When the Button is clicked, launch a camera app to take a picture. (You don't need to return the picture to the original app.)
     */
    @Test
    public void takePictureActionTest(){
        onView(allOf(is(instanceOf(android.widget.Button.class)),withText(equalToIgnoringCase("Take a Picture"))))
                .perform(click());
        // Check  launch a camera app
        intended(hasAction(MediaStore.ACTION_IMAGE_CAPTURE));
    }
}
