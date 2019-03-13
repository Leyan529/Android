package com.example.android.hellotoast;

import android.graphics.drawable.ColorDrawable;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.view.View;
import android.widget.TextView;

import org.hamcrest.Description;

public class IsBackgroundColor extends BoundedMatcher<View, TextView> {

    private final int expectedColor;
    private int realColor;

    public IsBackgroundColor(int expectedColor) {
        super(TextView.class);
        this.expectedColor = expectedColor;
    }

    @Override
    protected boolean matchesSafely(TextView item) {
        ColorDrawable background = (ColorDrawable) item.getBackground();
        realColor = background.getColor();
        return realColor == expectedColor;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("The color of background should be set as " + Integer.toString(expectedColor) + " instead of " + Integer.toString(realColor));
    }
}