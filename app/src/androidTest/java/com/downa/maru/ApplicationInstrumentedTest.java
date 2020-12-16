package com.downa.maru;

import android.widget.DatePicker;

import org.junit.Test;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class ApplicationInstrumentedTest {

    @Test
    public void applicationTest(){
        onView(withId(R.layout.activity_main));
        onView(withId(R.id.add_meeting)).perform(ViewActions.click());
        onView(withId(R.layout.activity_add_meeting)).check(matches(isDisplayed()));
        onView(withId(R.id.Create)).perform(ViewActions.click());
        onView(withId(R.layout.activity_main)).check(matches(isDisplayed()));
        onView(withId(R.layout.activity_main)).check(matches(hasMinimumChildCount(1)));
        onView(withId(R.menu.main_menu)).perform(ViewActions.click());
        onView(withId(R.id.date)).perform(ViewActions.click());
        onView(isAssignableFrom(DatePicker.class)).perform(setDate(2020,8,11));
        onView(withId(R.layout.activity_main)).check(matches(hasMinimumChildCount(1)));
        onView(withId(R.menu.main_menu)).perform(ViewActions.click());
        onView(withId(R.id.room_list)).perform(ViewActions.click());
        onView(withId(R.id.room_list)).perform(RecyclerViewAction.actionOnItemPosition(0,ViewActions.click()));
        onView(withId(R.layout.activity_main)).check(matches(hasMinimumChildCount(1)));
        onView(withId(R.menu.main_menu)).perform(ViewActions.click());
        onView(withId(R.id.no_filter)).perform(ViewActions.click());
        onView(withId(R.layout.activity_main)).check(matches(hasMinimumChildCount(1)));



    }
}
