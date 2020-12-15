package com.downa.maru;

import android.os.TestLooperManager;
import android.view.LayoutInflater;

import com.downa.maru.Controller.RoomGenerator;
import com.downa.maru.Model.Meeting;
import com.downa.maru.Model.Room;
import com.downa.maru.Service.ApiService;
import com.google.android.material.chip.Chip;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import DI.DI;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class MeetingServiceTest {

    private ApiService mService;

    @Before
    public void setup(){mService = DI.getNewInstanceApiService(); }

    public Meeting generateMeeting(){

        List<String> participant = Arrays.asList(
        "justine@gmail.com", "roger@gmail.com");

        Meeting meeting = new Meeting(RoomGenerator.generateRoom().get(0),participant,12,11,2020,22,30,"test");
        return meeting;
    }

    @Test
    public void addMeetingWithSuccess(){
        assertEquals(0,mService.getMeeting().size());
        Meeting meeting = generateMeeting();
        mService.createMeeting(meeting);
        assertEquals(1,mService.getMeeting().size());
    }

    @Test
    public  void getMeetingWithSuccess(){
        mService.createMeeting(generateMeeting());
        Meeting meeting = mService.getMeeting().get(0);
        assertTrue(mService.getMeeting().stream().map(Meeting::getDate).collect(Collectors.toList()).contains(meeting.getDate()));
        assertTrue(mService.getMeeting().stream().map(Meeting::getSubject).collect(Collectors.toList()).contains(meeting.getSubject()));
        assertTrue(mService.getMeeting().stream().map(Meeting::getParticipants).collect(Collectors.toList()).contains(meeting.getParticipants()));
        assertTrue(mService.getMeeting().stream().map(Meeting::getRoom).collect(Collectors.toList()).contains(meeting.getRoom()));
    }
    @Test
    public void deleteMeetingWithSuccess(){
        mService.createMeeting(generateMeeting());
        Meeting meetingToDelete = mService.getMeeting().get(0);
        mService.deleteMeeting(meetingToDelete);
        assertFalse(mService.getMeeting().contains(meetingToDelete));
    }

    @Test
    public void filterByRoomWIthSuccess(){
        mService.createMeeting(generateMeeting());
        Meeting meeting = mService.getMeeting().get(0);
        assertTrue(meeting.getRoom().getName().matches("Aphrodite"));
        assertFalse(meeting.getRoom().getName().matches("Zeus"));
    }

    @Test
    public void filterByDateWithSuccess(){
        mService.createMeeting((generateMeeting()));
        Meeting meeting = mService.getMeeting().get(0);
        Calendar c = Calendar.getInstance();
        c.set(2020,11,12,22,30);

        assertEquals(c.get(Calendar.YEAR,Calendar.MONTH,Calendar.DAY_OF_MONTH,Calendar.HOUR_OF_DAY,Calendar.MINUTE),meeting.getDate());
        assertFalse();
    }

}
