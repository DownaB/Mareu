package com.downa.maru.Service;

import com.downa.maru.Model.Meeting;
import com.downa.maru.Model.Room;

import java.util.List;

public interface ApiService {

    List <Meeting> getMeeting();

    void createMeeting(Meeting meeting);

    void deleteMeeting (Meeting meeting);

    List<Meeting> filterByRoom (String room);

    List<Meeting> filterByDate(int year, int month, int dayOfMonth);

}
