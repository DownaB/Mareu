package com.downa.maru.Service;


import com.downa.maru.Model.Meeting;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MeetingApiService implements ApiService {

    List<Meeting> meetings = new ArrayList<>();


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
    public List<Meeting> filterByRoom(String room) {
        final List<Meeting> byRoom = new ArrayList<>();
        for (Meeting theMeeting : meetings) {
            if (theMeeting.getRoom().getName().equals(room)) {
                byRoom.add(theMeeting);
            }
        }
        return byRoom;
    }

    @Override
    public List<Meeting> filterByDate(int year, int month, int dayOfMonth) {
        final List<Meeting> byHour = new ArrayList<>();
        final Calendar selectedDate = Calendar.getInstance();
        selectedDate.set(Calendar.YEAR, year);
        selectedDate.set(Calendar.MONTH, month);
        selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        for (Meeting theMeeting : meetings) {
            final Calendar mCalendar = Calendar.getInstance();
            mCalendar.setTimeInMillis(theMeeting.getDate());

            if (selectedDate.get(Calendar.YEAR) == mCalendar.get(Calendar.YEAR) && selectedDate.get(Calendar.MONTH) == mCalendar.get(Calendar.MONTH) && selectedDate.get(Calendar.DAY_OF_MONTH) == mCalendar.get(Calendar.DAY_OF_MONTH)) {
                byHour.add(theMeeting);
            }
        }
        return byHour;
    }

}
