/*
 * Copyright (C) 2018 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.sharedprefs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.Button;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getContext;
import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.is;
import static org.junit.Assume.assumeFalse;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class SharedPrefsTest {

    static private int team1RandPlus;
    static private int team1RandMinus;
    static private int team2RandPlus;
    static private int team2RandMinus;

    static private boolean clearFlag = true;

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


    // Name of shared preferences file
    private String sharedPrefFile =
            "com.example.android.sharedprefs";

    /**
     * Clears everything in the SharedPreferences
     */
    private void clearSharedPrefs(Context context) {
        SharedPreferences prefs =
                context.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.commit();
    }

    private void clickScoreButtons() {
        // Random press buttons
        team1RandPlus = (int) (Math.random()*2) + 1;
        team1RandMinus = (int) (Math.random()*3) + 3;
        team2RandPlus = (int) (Math.random()*2) + 1;
        team2RandMinus = (int) (Math.random()*3) + 3;
        for (int i=0; i<team1RandPlus; i++) {
            onView(withId(R.id.increaseTeam1)).perform(click());
        }
        for (int i=0; i<team1RandMinus; i++) {
            onView(withId(R.id.decreaseTeam1)).perform(click());
        }
        for (int i=0; i<team2RandPlus; i++) {
            onView(withId(R.id.increaseTeam2)).perform(click());
        }
        for (int i=0; i<team2RandMinus; i++) {
            onView(withId(R.id.decreaseTeam2)).perform(click());
        }
    }

    private void checkScoreTextViews(int score1, int score2) {
        onView(withId(R.id.score_1)).check(matches(withText(String.valueOf(score1))));
        onView(withId(R.id.score_2)).check(matches(withText(String.valueOf(score2))));
    }

    /**
     * 2. To test the app, rotate the device to ensure that configuration changes read the saved preferences and update the UI.
     */
    @Test
    public void rotateSavePrefs() {
        clickScoreButtons();
        mActivityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        checkScoreTextViews(team1RandPlus-team1RandMinus, team2RandPlus-team2RandMinus);
    }

    /**
     * 3. Stop the app and restart it to ensure that the preferences are saved.
     */
    @Test
    public void testSharedPref() {
        clickScoreButtons();
        mActivityRule.finishActivity();
        Intent startIntent = new Intent(Intent.ACTION_MAIN);
        clearFlag = false;
        mActivityRule.launchActivity(startIntent);
        clearFlag = true;
        checkScoreTextViews(team1RandPlus-team1RandMinus, team2RandPlus-team2RandMinus);
    }

    /**
     * 4. Add a Reset button that resets the score values to 0 and clears the shared preferences.
     */
    @Test
    public void testResetButton() {
        clickScoreButtons();
        onView(allOf(Matchers.<View>instanceOf(Button.class), withText(equalToIgnoringCase("reset")))).perform(click());
        checkScoreTextViews(0, 0);
        mActivityRule.finishActivity();
        Intent startIntent = new Intent(Intent.ACTION_MAIN);
        clearFlag = false;
        mActivityRule.launchActivity(startIntent);
        clearFlag = true;
        checkScoreTextViews(0, 0);
    }
}
