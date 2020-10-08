package com.downa.maru.Controller;

import java.util.List;

import Model.Meeting;

public class MeetingApiService implements ApiService {

    List<Meeting> meetings;

    @Override
    public List<Meeting> getMeeting() { return meetings; }

    @Override
    public void createMeeting(Meeting meeting) { meetings.add(meeting); }

    @Override
    public void deleteMeeting(Meeting meeting) {meetings.remove(meeting);}
}
