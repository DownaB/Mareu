package com.downa.maru;

import android.view.LayoutInflater;

import com.downa.maru.Model.Meeting;
import com.downa.maru.Service.ApiService;
import com.google.android.material.chip.Chip;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import DI.DI;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MeetingServiceTest {

    private ApiService mService;

    @Before
    public void setup(){mService = DI.getNewInstanceApiService(); }

    @Test
    public void addMeetingWithSuccess(){
        Meeting meeting = mService.getMeeting().get(0);
        mService.createMeeting(meeting);
        assertEquals(1,mService.getMeeting().size());
    }

    @Test
    public  void getMeetingWithSuccess(){
        Meeting meeting = mService.getMeeting().get(0);
        mService.createMeeting(meeting);
        assertEquals(1,mService.getMeeting().size());
        assertTrue(mService.getMeeting().stream().map(Meeting::getDate).collect(Collectors.toList()).contains(meeting.getDate()));
        assertTrue(mService.getMeeting().stream().map(Meeting::getSubject).collect(Collectors.toList()).contains(meeting.getSubject()));
        assertTrue(mService.getMeeting().stream().map(Meeting::getParticipants).collect(Collectors.toList()).contains(meeting.getParticipants()));
        assertTrue(mService.getMeeting().stream().map(Meeting::getRoom).collect(Collectors.toList()).contains(meeting.getRoom()));
    }
    @Test
    public void deleteMeetingWithSuccess(){
        Meeting meetingToDelete = mService.getMeeting().get(0);
        mService.deleteMeeting(meetingToDelete);
        assertFalse(mService.getMeeting().contains(meetingToDelete));
    }

}
