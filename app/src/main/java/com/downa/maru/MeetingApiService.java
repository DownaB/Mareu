package com.downa.maru;

import java.util.List;

public class MeetingApiService implements ApiService {

    List <Meeting> meetings;

    @Override
    public List<Meeting> getMeeting() { return meetings; }

    @Override
    public void deleteMeeting(Meeting meeting) {meetings.remove(meeting); }

    @Override
    public void createMeeting(Meeting meeting) {meetings.add(meeting);

    }
}
