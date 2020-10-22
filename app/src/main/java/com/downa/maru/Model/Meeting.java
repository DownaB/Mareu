package com.downa.maru.Model;

import com.google.android.material.chip.Chip;

public class Meeting {

    private String organisateur;
    private Chip participant;
    private long date;
    private long hour;
    private String subject;
    private Room room;


    public Meeting(Room room, String organisateur, Chip participant, long date, long hour, String subject) {

        this.organisateur = organisateur;
        this.participant = participant;
        this.date = date;
        this.hour = hour;
        this.subject = subject;
        this.room = room;


    }

    public String getOrganisateur() {
        return organisateur;
    }

    public void setOrganisateur(String organisateur) {
        this.organisateur = organisateur;
    }

    public Chip getParticipant() {
        return participant;
    }

    public void setParticipant(Chip participant) {
        this.participant = participant;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public long getHour() {
        return hour;
    }

    public void setHour(long hour) {
        this.hour = hour;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
