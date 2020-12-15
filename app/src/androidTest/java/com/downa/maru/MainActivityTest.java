package com.downa.maru;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.util.Patterns;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.downa.maru.Controller.RoomGenerator;
import com.downa.maru.Model.Meeting;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class MainActivityTest {



    @Test
    public void checkIfClickAddMeeting_NewActivityLaunched(){
        onView(withId(R.layout.activity_main));
        onView(withId(R.id.add_meeting)).perform(ViewActions.click());
        onView(withId(R.layout.activity_add_meeting)).check(matches(isDisplayed()));
        Espresso.pressBack();
    }

    @Test
    public void checkIfClickMenu_DatePickerLaunched(){
        onView(withId(R.layout.activity_main));
        onView(withId(R.menu.main_menu));
        onView(withId(R.id.date)).perform(ViewActions.click());
        onView(isAssignableFrom(DatePicker.class)).check(matches(isDisplayed()));

    }

    @Test
    public void checkIfClickMenu_SubMenuLaunched(){
        onView(withId(R.layout.activity_main));
        onView(withId(R.menu.main_menu));
        onView(withId(R.id.room_list)).perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));
        onView(withId(R.id.room_list)).check(matches(withText("Aphrodite")));
    }


}
