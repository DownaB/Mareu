package com.downa.maru.Model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

public class Meeting {

    private List<String> participants;
    private long date;
    private String subject;
    private Room room;


    public Meeting(Room room, List<String> participants, int day, int month, int year, int hour, int minute, String subject) {

        this.participants = participants;
        this.date = initGetTimeInMillis(day, month, year, hour, minute);
        this.subject = subject;
        this.room = room;
    }


    private long initGetTimeInMillis(int day, int month, int year, int hour, int minute) {
        Calendar c = Calendar.getInstance();
        c.set(year, month, day, hour, minute);
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

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getHour(){
        final Date formatDate = new Date(this.date);
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH'h'mm", Locale.FRENCH);
        return simpleDateFormat.format(formatDate);
    }
}
