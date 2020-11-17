package com.downa.maru.Model;

import com.google.android.material.chip.Chip;

import java.util.Calendar;
import java.util.List;

public class Meeting {

    private List<String> participants;
    private long date;
    private String subject;
    private List <Room> room;


    public Meeting(List<Room> room, List<String> participants, int day, int month, int year, int hour, int minute, String subject) {

        this.participants = participants;
        this.date = initGetTimeInMillis(day, month, year,  hour,  minute);
        this.subject = subject;
        this.room = room;


    }

    private long initGetTimeInMillis (int day, int month, int year, int hour, int minute){
        Calendar c = Calendar.getInstance();
        c.set(day, month, year, hour, minute);
        return c.getTimeInMillis();
    }


    public List<String> getParticipants() {
        return participants;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
