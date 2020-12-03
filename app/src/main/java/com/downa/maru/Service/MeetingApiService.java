package com.downa.maru.Service;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;

import com.downa.maru.Controller.RoomGenerator;
import com.downa.maru.Model.Meeting;
import com.downa.maru.Model.Room;
import com.downa.maru.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MeetingApiService implements ApiService {

    List<Meeting> meetings;

    private int day = -1;
    private int month = -1;
    private int year = -1;

    @Override
    public List<Meeting> getMeeting() {
        return meetings;
    }

    @Override
    public void createMeeting(Meeting meeting) {
        meetings.add(meeting);
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        meetings.remove(meeting);
    }

    @Override
    public List<Meeting> filterByRoom(Room room) {
        final List<Meeting> byRoom = new ArrayList<>();
        for (Meeting theMeeting : meetings) {
            if (theMeeting.getRoom().getName().equals(room.getName())) {
                byRoom.add(theMeeting);
            }
        }
        return byRoom;
    }

    @Override
    public List<Meeting> filterByDate() {
        final List<Meeting> byHour = new ArrayList<>();

        final DatePicker.OnDateChangedListener listener = new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int selectedDay, int selectedMonth, int selectedYear) {
                day=selectedDay;
                month=selectedMonth;
                year= selectedYear;
            }
        };
        final Calendar c = Calendar.getInstance();
        final DatePicker mDatePicker = new DatePicker(this, listener, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));

        for (Meeting theMeeting : meetings) {
            if (theMeeting.getDate()== day+month+year) {
                byHour.add(theMeeting);
            }
        }
        return byHour;
    }
}
