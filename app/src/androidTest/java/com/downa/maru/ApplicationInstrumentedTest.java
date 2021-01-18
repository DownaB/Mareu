package com.downa.maru;

import android.widget.DatePicker;
import android.widget.TimePicker;

import com.downa.maru.Controller.MainActivity;

import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;

import androidx.appcompat.widget.MenuPopupWindow;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.matcher.RootMatchers;/**/
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

public class ApplicationInstrumentedTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void applicationTest(){

        onView(withId(R.id.add_meeting)).perform(ViewActions.click());
        onView(withId(R.id.RoomMeeting)).perform(ViewActions.click());
        onView(withText("Zeus")).perform(ViewActions.click());
        onView(withId(R.id.select_date)).perform(ViewActions.click());
        onView(isAssignableFrom(DatePicker.class)).perform(PickerActions.setDate(2020,12 ,8));
        onView(withId(android.R.id.button1)).perform(ViewActions.click());
        onView(withId(R.id.select_hour)).perform(ViewActions.click());
        onView(isAssignableFrom(TimePicker.class)).perform(PickerActions.setTime(13,00));
        onView(withId(android.R.id.button1)).perform(ViewActions.click());
        onView(withId(R.id.Input)).perform(ViewActions.typeText(String.valueOf("android@gmail.com")));
        onView(withId(R.id.Btn_add)).perform(ViewActions.scrollTo(),ViewActions.click());
        onView(withId(R.id.Subject)).perform(ViewActions.scrollTo(),ViewActions.typeText(String.valueOf("texte")));
        onView(withId(R.id.Create)).perform(ViewActions.scrollTo(),ViewActions.click());
        onView(withId(R.id.meeting_recyclerview)).check(matches(hasMinimumChildCount(1)));
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onData(CoreMatchers.anything()).inRoot(RootMatchers.isPlatformPopup())
                .inAdapterView(CoreMatchers.instanceOf(MenuPopupWindow.MenuDropDownListView.class)).atPosition(0).perform(ViewActions.click());
        onView(isAssignableFrom(DatePicker.class)).perform(PickerActions.setDate(2020,12,4));
        onView(withId(android.R.id.button1)).perform(ViewActions.click());
        onView(withId(R.id.meeting_recyclerview)).check(matches(hasMinimumChildCount(0)));
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onData(CoreMatchers.anything()).inRoot(RootMatchers.isPlatformPopup())
                .inAdapterView(CoreMatchers.instanceOf(MenuPopupWindow.MenuDropDownListView.class)).atPosition(0).perform(ViewActions.click());
        onView(isAssignableFrom(DatePicker.class)).perform(PickerActions.setDate(2020,12,8));
        onView(withId(android.R.id.button1)).perform(ViewActions.click());
        onView(withId(R.id.meeting_recyclerview)).check(matches(hasMinimumChildCount(1)));
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onData(CoreMatchers.anything()).inRoot(RootMatchers.isPlatformPopup())
                .inAdapterView(CoreMatchers.instanceOf(MenuPopupWindow.MenuDropDownListView.class)).atPosition(1).perform(ViewActions.click());
        onView(withText("Aphrodite")).perform(ViewActions.click());
        onView(withId(R.id.meeting_recyclerview)).check(matches(hasMinimumChildCount(0)));
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onData(CoreMatchers.anything()).inRoot(RootMatchers.isPlatformPopup())
                .inAdapterView(CoreMatchers.instanceOf(MenuPopupWindow.MenuDropDownListView.class)).atPosition(1).perform(ViewActions.click());
        onView(withText("Zeus")).perform(ViewActions.click());
        onView(withId(R.id.meeting_recyclerview)).check(matches(hasMinimumChildCount(1)));
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onData(CoreMatchers.anything()).inRoot(RootMatchers.isPlatformPopup())
                .inAdapterView(CoreMatchers.instanceOf(MenuPopupWindow.MenuDropDownListView.class)).atPosition(2).perform(ViewActions.click());
        onView(withId(R.id.meeting_recyclerview)).check(matches(hasMinimumChildCount(1)));



    }
}
