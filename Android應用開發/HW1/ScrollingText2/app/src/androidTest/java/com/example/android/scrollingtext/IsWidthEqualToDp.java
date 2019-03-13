package com.example.android.scrollingtext;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.view.View;
import android.widget.TextView;

import org.hamcrest.Description;


public class IsWidthEqualToDp extends BoundedMatcher<View, TextView> {

    private final int expectedDp;
    private final float scale;
    private int realWidth;

    public IsWidthEqualToDp(int expectedDp, float scale) {
        super(TextView.class);
        this.expectedDp = expectedDp;
        this.scale = scale;
    }

    /**
     * Convert dp to px
     *
     * @param dp
     * @return
     * @see <a hreh="https://developer.android.com/training/multiscreen/screendensities">Support different pixel densities</a>
     */
    private int convertDpToPx(int dp) {
        return (int) (dp * scale + 0.5f);
    }

    /**
     * Compare getWidth() and expectedDp in px
     * https://developer.android.com/reference/android/view/View.html#getWidth()
     *
     * @param item
     * @return
     */
    @Override
    protected boolean matchesSafely(TextView item) {
        realWidth = item.getWidth();
        return realWidth == convertDpToPx(expectedDp);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("The expected width is " + expectedDp + " dp, which equals " + convertDpToPx(expectedDp) + " px, but the real size is " + realWidth + " px");
    }
}

