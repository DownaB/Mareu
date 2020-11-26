package com.downa.maru.Service;

import com.downa.maru.Controller.RoomGenerator;
import com.downa.maru.Model.Meeting;
import com.downa.maru.Model.Room;

import java.util.ArrayList;
import java.util.List;

public class MeetingApiService implements ApiService {

    List<Meeting> meetings;
    List<Room> room = RoomGenerator.generateRoom();

    @Override
    public List<Meeting> getMeeting() { return meetings; }

    @Override
    public void createMeeting(Meeting meeting) { meetings.add(meeting); }

    @Override
    public void deleteMeeting(Meeting meeting) {meetings.remove(meeting);}


    public void filterByRoom (Meeting meeting){
        final List <Meeting> byRoom = new ArrayList<>();
        for (Meeting theMeeting : meetings){
            if (theMeeting.getRoom() == meeting.getRoom()){
                byRoom.add(theMeeting); ;
            }
        }
    }

    public void filterByDate(Meeting meeting){
        final List<Meeting> byHour = new ArrayList<>();
        for (Meeting theMeeting: meetings){
            if (theMeeting.getDate() == meeting.getDate()){
                byHour.add(theMeeting);
            }
        }

    }
}
