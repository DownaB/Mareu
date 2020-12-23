package com.downa.maru;

import android.widget.DatePicker;
import android.widget.TimePicker;

import com.downa.maru.Controller.MainActivity;

import org.junit.Rule;
import org.junit.Test;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

public class ApplicationInstrumentedTest {

    @Rule
    public ActivityScenarioRule mActivityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void applicationTest(){

        onView(withId(R.id.add_meeting)).perform(ViewActions.click());
        onView(withId(R.id.RoomMeeting)).perform(RecyclerViewAction.actionOnItemPosition(0,ViewActions.click()));
        onView(withId(R.id.select_date)).perform(ViewActions.click());
        onView(isAssignableFrom(DatePicker.class)).perform(PickerActions.setDate(2020,11,8));
        onView(withId(R.id.select_hour)).perform(ViewActions.click());
        onView(isAssignableFrom(TimePicker.class)).perform(PickerActions.setTime(13,00));
        onView(withId(R.id.Input)).perform(ViewActions.typeText(String.valueOf("@")));
        onView(withId(R.id.Btn_add)).perform(ViewActions.click());
        onView(withId(R.id.Subject)).perform(ViewActions.typeText(String.valueOf("texte")));
        onView(withId(R.id.Create)).perform(ViewActions.click());
        onView(withId(R.id.meeting_recyclerview)).check(matches(hasMinimumChildCount(1)));
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withId(R.id.date)).perform(ViewActions.click());
        onView(isAssignableFrom(DatePicker.class)).perform(PickerActions.setDate(2020,11,8));
        onView(withId(R.id.meeting_recyclerview)).check(matches(hasMinimumChildCount(1)));
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withId(R.id.room_list)).perform(ViewActions.click());
        onView(withText("Aphrodite")).inRoot(RootMatchers.isPlatformPopup()).check(matches(isDisplayed()));
        onView(withId(R.id.meeting_recyclerview)).check(matches(hasMinimumChildCount(1)));
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withId(R.id.no_filter)).perform(ViewActions.click());
        onView(withId(R.id.meeting_recyclerview)).check(matches(hasMinimumChildCount(1)));



    }
}
