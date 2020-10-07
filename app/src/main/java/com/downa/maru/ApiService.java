package com.downa.maru;

import java.util.List;

public interface ApiService {

    List<Meeting> getMeeting();

    void deleteMeeting(Meeting meeting);

    void createMeeting (Meeting meeting);
}
