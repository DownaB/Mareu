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

    @Override
    public boolean isRoomAvailable(Meeting meeting) {
        List<Meeting> mMeetings = filterByRoom(meeting.getRoom().getName());

        Calendar date = Calendar.getInstance();
        date.setTimeInMillis(meeting.getDate());

        Calendar dateOut = Calendar.getInstance();
        dateOut.setTimeInMillis(meeting.getDateOut());

        boolean hasConflict = false;

        for (int i = 0; i < mMeetings.size(); i++) {

            Calendar date1 = Calendar.getInstance();
            date1.setTimeInMillis(mMeetings.get(i).getDate());

            Calendar dateOut1 = Calendar.getInstance();
            dateOut1.setTimeInMillis(mMeetings.get(i).getDateOut());

            if (date.get(Calendar.YEAR) == date1.get(Calendar.YEAR) && date.get(Calendar.MONTH) == date1.get(Calendar.MONTH) && date.get(Calendar.DAY_OF_MONTH) == date1.get(Calendar.DAY_OF_MONTH)) {
                if (date.get(Calendar.HOUR_OF_DAY) >= date1.get(Calendar.HOUR_OF_DAY) && date.get(Calendar.HOUR_OF_DAY) <= dateOut1.get(Calendar.HOUR_OF_DAY)) {
                    hasConflict = true;
                } else if
                (dateOut.get(Calendar.HOUR_OF_DAY) >= date1.get(Calendar.HOUR_OF_DAY) && dateOut.get(Calendar.HOUR_OF_DAY) <= dateOut1.get(Calendar.HOUR_OF_DAY))
                {
                    hasConflict = true;
                }
                if (hasConflict == true) {
                    break;
                }
            }
        }

        return hasConflict == false;

    }
    }

