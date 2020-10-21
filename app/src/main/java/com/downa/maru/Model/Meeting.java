package com.downa.maru.Model;

public class Meeting {

    private String organisateur;
    private String participant;
    private String date;
    private String hour;
    private String subject;
    private Room room;


    public Meeting(Room room, String organisateur, String participant, String date, String hour, String subject) {

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

    public String getParticipant() {
        return participant;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
