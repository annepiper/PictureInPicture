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
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityPIP1 {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityPIP1() {
        ViewInteraction textView = onView(
                allOf(withId(R.id.explanation), withText("This sample demonstrates basic usage of Picture-in-Picture mode for handheld devices. The sample plays a video. The video keeps on playing when the app is turned in to Picture-in-Picture mode. On Picture-in-Picture screen, the app shows an action item to pause or resume the video."),
                        childAtPosition(
                                allOf(withId(R.id.vertical),
                                        childAtPosition(
                                                withId(R.id.scroll),
                                                0)),
                                1),
                        isDisplayed()));
        textView.check(matches(withText("demonstrates basic usage of Picture-in-Picture mode for handheld devices. The sample plays a video. The video keeps on playing when the app is turned in to Picture-in-Picture mode. On Picture-in-Picture screen, the app shows an action item to pause or resume the video.")));

        ViewInteraction button = onView(
                allOf(withId(R.id.switch_example),
                        childAtPosition(
                                allOf(withId(R.id.vertical),
                                        childAtPosition(
                                                withId(R.id.scroll),
                                                0)),
                                2),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction button2 = onView(
                allOf(withId(R.id.pip),
                        childAtPosition(
                                allOf(withId(R.id.vertical),
                                        childAtPosition(
                                                withId(R.id.scroll),
                                                0)),
                                0),
                        isDisplayed()));
        button2.check(matches(isDisplayed()));

        ViewInteraction view = onView(
                allOf(withId(R.id.surface),
                        childAtPosition(
                                allOf(withId(R.id.movie),
                                        childAtPosition(
                                                withId(R.id.activity_main),
                                                0)),
                                0),
                        isDisplayed()));
        view.check(matches(isDisplayed()));
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
