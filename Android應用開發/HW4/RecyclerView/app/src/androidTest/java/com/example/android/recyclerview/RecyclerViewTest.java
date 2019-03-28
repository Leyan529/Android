package com.example.android.recyclerview;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.PositionAssertions.isCompletelyAbove;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasSibling;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.example.android.recyclerview.RecyclerViewTest.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.IsEqual.equalTo;


/**
 * Tests for the 11.Homework "Build and run an app" in Android fundamentals 04.5: RecyclerView
 *
 * @see <a href="https://codelabs.developers.google.com/codelabs/android-training-create-recycler-view/index.html?index=..%2F..%2Fandroid-training#10">04.5: RecyclerView</a>
 */
@RunWith(AndroidJUnit4.class)
public class RecyclerViewTest {

    @Rule
    public IntentsTestRule<MainActivity> mIntentsRule = new IntentsTestRule<>(MainActivity.class);
    private static int listSize = 1;

    /**
     * Create an app that uses a RecyclerView to display a list of recipes. Each list item must show the name of the recipe with a short description.
     * Use separate TextView elements and styling for the recipe name and description.
     */
    @Test
    public void testRecyclerViewLayout() {
        // check whether recycleView exist
        onView(Matchers.<View>instanceOf(RecyclerView.class)).check(matches(isDisplayed()));
        // check item number is greater than 0
        onView(Matchers.<View>instanceOf(RecyclerView.class)).check(withItemCount(greaterThan(0)));
        // check item layout hierarchy
        onView(withText("Recipe 0")).check(matches(withParent(Matchers.<View>instanceOf(LinearLayout.class))));
        onView(withText(containsString("description of Recipe 1"))).check(matches(withParent(Matchers.<View>instanceOf(LinearLayout.class))));
        onView(withText("Recipe 0")).check(matches(hasSibling(withText(containsString("description of Recipe 0")))));
        onView(withText("Recipe 0")).check(isCompletelyAbove(withText(containsString("description of Recipe 0"))));
    }

    /**
     * When the user taps a recipe (an item in the list), start an Activity that shows the full recipe text.
     * Use separate TextView elements and styling for the recipe name and description.
     */
    @Test
    public void testRecyclerViewOnClick() {
        // generate the item number randomly
        Random random = new Random();
        int selected = random.nextInt(listSize);
        // click an item randomly
        onView(Matchers.<View>instanceOf(RecyclerView.class)).perform(RecyclerViewActions.actionOnItemAtPosition(selected, click()));
        mIntentsRule.getActivity();
        // check whether clicked position is delivered correctly
        onView(withText(containsString("ingredients of Recipe " + Integer.toString(selected)))).check(matches(isDisplayed()));
        // check up button
        onView(withContentDescription("Navigate up")).perform(click());
        assertThat(mIntentsRule.getActivity().getClass().getName(), equalTo(MainActivity.class.getName()));
        
    }


    public static class RecyclerViewItemCountAssertion implements ViewAssertion {
        private final Matcher<Integer> matcher;

        public static RecyclerViewItemCountAssertion withItemCount(int expectedCount) {
            return withItemCount(is(expectedCount));
        }

        public static RecyclerViewItemCountAssertion withItemCount(Matcher<Integer> matcher) {
            return new RecyclerViewItemCountAssertion(matcher);
        }

        private RecyclerViewItemCountAssertion(Matcher<Integer> matcher) {
            this.matcher = matcher;
        }

        @Override
        public void check(View view, NoMatchingViewException noViewFoundException) {
            if (noViewFoundException != null) {
                throw noViewFoundException;
            }

            RecyclerView recyclerView = (RecyclerView) view;
            RecyclerView.Adapter adapter = recyclerView.getAdapter();
            RecyclerViewTest.listSize = adapter.getItemCount();
            assertThat(adapter.getItemCount(), matcher);
        }
    }
    
}
