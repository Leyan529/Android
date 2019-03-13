package com.example.android.scrollingtext;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.hamcrest.Description;

public class isOrientation extends BoundedMatcher<View, LinearLayout> {

    private static int expectedOrientation;
    private int realOrientaion;
    public isOrientation(int expectedOrientation){
        super(LinearLayout.class);
        this.expectedOrientation = expectedOrientation;
    }
    @Override
    protected boolean matchesSafely(LinearLayout item) {
        realOrientaion = item.getOrientation();
        return realOrientaion == expectedOrientation;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("The expected orientation is " + expectedOrientation + ", but the real orientation is " + realOrientaion );
    }
}
