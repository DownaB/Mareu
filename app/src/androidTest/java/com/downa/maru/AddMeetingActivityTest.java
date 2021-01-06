package com.downa.maru;

import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.downa.maru.Controller.AddMeetingActivity;

import org.junit.Rule;
import org.junit.Test;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class AddMeetingActivityTest {

    @Rule
    public ActivityScenarioRule<AddMeetingActivity> mActivityScenarioRule = new ActivityScenarioRule<>(AddMeetingActivity.class);

    @Test
    public void setDateInDatePicker(){

        onView(withId(R.id.select_date)).perform(ViewActions.click());
        onView(isAssignableFrom(DatePicker.class)).check(matches(isDisplayed()));
        onView(isAssignableFrom(DatePicker.class)).perform(PickerActions.setDate(2020,11 ,8));
        onView(withId(android.R.id.button1)).perform(ViewActions.click());
        onView(withId(R.id.Date)).check(matches(withText("2020/12/8")));

    }

    @Test
    public void setTimeInTimePicker(){

        onView(withId(R.id.select_hour)).perform(ViewActions.click());
        onView(isAssignableFrom(TimePicker.class)).check(matches(isDisplayed()));
        onView(isAssignableFrom(TimePicker.class)).perform(PickerActions.setTime(13,00));
        onView(withId(android.R.id.button1)).perform(ViewActions.click());
        onView(withId(R.id.Hour)).check(matches(withText("13:00")));
    }


    @Test
    public void addChipOnChipGroup(){

        onView(withId(R.id.Input)).perform(ViewActions.typeText(("android@gmail")));
        onView(withId(R.id.Btn_add)).perform(ViewActions.click());
        onView(withText(R.string.mail_non_valide)).check(matches(isDisplayed()));
        onView(withId(R.id.Participant)).check(matches(hasMinimumChildCount(0)));
        onView(withId(R.id.Input)).perform(ViewActions.typeText(("android@gmail.com")));
        onView(withId(R.id.Btn_add)).perform(ViewActions.click());
        onView(withId(R.id.Participant)).check(matches(hasMinimumChildCount(1)));

    }

}
