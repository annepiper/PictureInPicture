package com.example.android.pictureinpicture;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityPIP2 {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityPIP2() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.switch_example), withText("Switch to using MediaSession"),
                        childAtPosition(
                                allOf(withId(R.id.vertical),
                                        childAtPosition(
                                                withId(R.id.scroll),
                                                0)),
                                2)));

        appCompatButton.check(matches(withText("Switch to using MediaSession")));
        appCompatButton.perform(scrollTo(), click());



        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.pip), withText("Enter Picture-in-Picture mode"),
                        childAtPosition(
                                allOf(withId(R.id.vertical),
                                        childAtPosition(
                                                withId(R.id.scroll),
                                                0)),
                                0)));
        appCompatButton2.check(matches(withText("Enter Picture-in-Picture mode")));

       // appCompatButton2.perform(scrollTo(), click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.switch_example), withText("Switch to custom actions example"),
                        childAtPosition(
                                allOf(withId(R.id.vertical),
                                        childAtPosition(
                                                withId(R.id.scroll),
                                                0)),
                                2)));
        appCompatButton3.check(matches(withText("Switch to custom actions example")));

        appCompatButton3.perform(scrollTo(), click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
