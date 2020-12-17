package com.downa.maru;

import android.util.Patterns;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.downa.maru.Controller.AddMeetingActivity;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;

import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class AddMeetingActivityTest {

    @Rule
    public ActivityScenarioRule mActivityScenarioRule = new ActivityScenarioRule<>(AddMeetingActivity.class);

    @Test
    public void setDateInDatePicker(){

        onView(withId(R.id.select_date)).perform(ViewActions.click());
        onView(isAssignableFrom(DatePicker.class)).check(matches(isDisplayed()));
        onView(isAssignableFrom(DatePicker.class)).perform(PickerActions.setDate(2020,11,8));
        onView(withId(R.id.Date)).check(matches(withText("8/12/2020")));

    }

    @Test
    public void setTimeInTimePicker(){

        onView(withId(R.id.select_hour)).perform(ViewActions.click());
        onView(isAssignableFrom(TimePicker.class)).check(matches(isDisplayed()));
        onView(isAssignableFrom(TimePicker.class)).perform(PickerActions.setTime(13,00));
        onView(withId(R.id.Hour)).check(matches(withText("13:00")));
    }


    @Test
    public void addChipOnChipGroup(){

        onView(withId(R.id.Input)).perform(ViewActions.typeText(String.valueOf(Patterns.EMAIL_ADDRESS.matcher().matches())));
        onView(withId(R.id.Btn_add)).perform(ViewActions.click());
        onView(withId(R.id.Participant)).check(matches(hasMinimumChildCount(1)));
        onView(withId(R.id.Input)).perform(ViewActions.typeText(String.valueOf("texte")));
        onView(withId(R.id.Btn_add)).perform(ViewActions.click());
        onView(withId(R.id.Participant)).check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void addMeeting(){

        onView(withId(R.id.Create)).perform(ViewActions.click());
        onView(Matchers.allOf(isDisplayed(),ViewMatchers.withId(R.id.meeting_recyclerview))).check((ViewAssertion) hasChildCount(1));
    }

}
