package com.downa.maru.Service;

import com.downa.maru.Model.Meeting;

import java.util.List;

public interface ApiService {

    List <Meeting> getMeeting();

    void createMeeting(Meeting meeting);

    void deleteMeeting (Meeting meeting);
}
