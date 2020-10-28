package com.downa.maru.Model;

import com.google.android.material.chip.Chip;

import java.util.List;

public class Meeting {

    private List<String> participants;
    private long date;
    private String subject;
    private Room room;


    public Meeting(Room room, List<String> participants, long date, String subject) {

        this.participants = participants;
        this.date = date;
        this.subject = subject;
        this.room = room;


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
