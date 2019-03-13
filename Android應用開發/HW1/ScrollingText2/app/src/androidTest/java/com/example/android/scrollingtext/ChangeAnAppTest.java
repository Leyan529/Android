package com.example.android.scrollingtext;

import android.content.pm.ActivityInfo;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.w3c.dom.Text;

import java.util.Iterator;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.PositionAssertions.isCompletelyLeftOf;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.hasSibling;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withTagKey;
import static android.support.test.espresso.util.TreeIterables.breadthFirstViewTraversal;
import static android.support.test.espresso.util.TreeIterables.depthFirstViewTraversal;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;

/**
 * Tests for the 11.Homework "Change an app" in Android fundamentals 01.3: Text and scrolling views
 * <p>
 * [1] Change the subheading so that it wraps within a column on the left that is 100 dp wide, as shown below.
 * [2] Place the text of the article to the right of the subheading as shown below.
 *
 * @see <a href="https://codelabs.developers.google.com/codelabs/android-training-text-and-scrolling-views/index.html?index=..%2F..%2Fandroid-training#10">01.3: Text and scrolling views</a>
 */
@RunWith(AndroidJUnit4.class)
public class ChangeAnAppTest {

    private static final int DP_SIZE = 100;

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    /**
     * The layout shows the subheading in the left column and the article text in the right column, as shown in the above figure.
     */
    @Test
    public void testTextViewTwoColumn() {
        // Check that the width of subheading 100dp
        final float scale = mActivityRule.getActivity().getResources().getDisplayMetrics().density;
        onView(withId(R.id.article_subheading)).check(matches(new IsWidthEqualToDp(DP_SIZE, scale)));
        // Check subheading is on the left of article
        onView(withId(R.id.article_subheading)).check(isCompletelyLeftOf(withId(R.id.article)));
    }

    /**
     * The LinearLayout orientation is set to horizontal.
     */
    @Test
    public void testLinearLayoutOrientation() {
        onView(allOf(withParent(Matchers.<View>instanceOf(ScrollView.class)), Matchers.<View>instanceOf(LinearLayout.class))).check(matches(new isOrientation(LinearLayout.HORIZONTAL)));
    }

}
