package com.downa.maru.Controller;

import java.util.List;

import Model.Meeting;

public interface ApiService {

    List <Meeting> getMeeting();

    void createMeeting(Meeting meeting);

    void deleteMeeting (Meeting meeting);
}
