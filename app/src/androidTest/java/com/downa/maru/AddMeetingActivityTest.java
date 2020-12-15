package com.downa.maru;

import android.util.Patterns;
import android.widget.DatePicker;
import android.widget.TimePicker;

import org.hamcrest.Matchers;
import org.junit.Test;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class AddMeetingActivityTest {

    @Test
    public void setDateInDatePicker(int year, int month, int dayOfMonth){
        onView(withId(R.layout.activity_add_meeting));
        onView(withId(R.id.select_date)).perform(ViewActions.click());
        onView(isAssignableFrom(DatePicker.class)).perform(setDateInDatePicker(2020,12,8));
        onView(withId(R.id.Date)).check(matches(withText("8/12/2020")));

    }

    @Test
    public void setTimeInTimePicker(int hour, int minute){
        onView(withId(R.layout.activity_add_meeting));
        onView(withId(R.id.select_hour)).perform(ViewActions.click());
        onView(isAssignableFrom(TimePicker.class)).perform(setTimeInTimePicker(13,00));
        onView(withId(R.id.Hour)).check(matches(withText("13:00")));
    }

    @Test
    public void spinnerTest(){
        onView(withId(R.layout.activity_add_meeting));
        onView(withId(R.id.RoomMeeting)).perform(ViewActions.click());
        onView(Matchers.allOf(isDisplayed(), ViewMatchers.withId(R.id.RoomMeeting)))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, ViewActions.click()));
        onView(withId(R.id.RoomMeeting)).check(matches(withText("Apollon")));
    }

    @Test
    public void addChipOnChipGroup(String mail){
        onView(withId(R.layout.activity_add_meeting));
        onView(withId(R.id.Input)).perform(ViewActions.typeText(String.valueOf(Patterns.EMAIL_ADDRESS.matcher(mail).matches())));
        onView(withId(R.id.Btn_add)).perform(ViewActions.click());
        onView(withId(R.id.Participant)).check(matches(isDisplayed()));
    }

    @Test
    public void addMeeting(){
        onView(withId(R.layout.activity_add_meeting));
        onView(withId(R.id.Create)).perform(ViewActions.click());
        onView(Matchers.allOf(isDisplayed(),ViewMatchers.withId(R.id.meeting_recyclerview))).check(withItemCount(1));
    }

}
