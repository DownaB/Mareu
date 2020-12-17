package com.downa.maru;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.util.Patterns;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.downa.maru.Controller.MainActivity;
import com.downa.maru.Controller.RoomGenerator;
import com.downa.maru.Model.Meeting;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import androidx.appcompat.widget.MenuPopupWindow;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.Root;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

public class MainActivityTest {



    @Rule
    public ActivityScenarioRule<MainActivity> mActivityRule = new ActivityScenarioRule<>(MainActivity.class);



    @Test
    public void checkIfClickAddMeeting_NewActivityLaunched(){
        onView(withId(R.id.add_meeting)).perform(ViewActions.click());
        onView(withId(R.id.Create)).check(matches(isDisplayed()));
    }

    @Test
    public  void checkIfMenuLaunched(){
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("date")).inRoot(RootMatchers.isPlatformPopup()).check(matches(isDisplayed()));
    }

    @Test
    public void checkIfClickMenu_DatePickerLaunched(){
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onData(CoreMatchers.anything()).inRoot(RootMatchers.isPlatformPopup())
                .inAdapterView(CoreMatchers.instanceOf(MenuPopupWindow.MenuDropDownListView.class)).atPosition(0).perform(ViewActions.click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).check(matches(isDisplayed()));

    }

    @Test
    public void checkIfClickMenu_SubMenuLaunched(){
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onData(CoreMatchers.anything()).inRoot(RootMatchers.isPlatformPopup())
                .inAdapterView(CoreMatchers.instanceOf(MenuPopupWindow.MenuDropDownListView.class)).atPosition(1).perform(ViewActions.click());
        onView(withText("Aphrodite")).inRoot(RootMatchers.isPlatformPopup()).check(matches(isDisplayed()));
    }


}
