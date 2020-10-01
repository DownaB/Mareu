package com.downa.maru;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Meeting  {

    private String mName;
    private String mParticipant;
    private String mHours;
    private String mSubject;
    private String mAvatar;

    public Meeting (String mAvatar,String mName, String mHours,String mSubject,String mParticipant){

        this.mAvatar=mAvatar;
        this.mName=mName;
        this.mHours=mHours;
        this.mSubject=mSubject;
        this.mParticipant=mParticipant;
    }

    public String getName() { return mName; }

    public void setName(String name) { mName = name; }

    public String getParticipant() { return mParticipant; }

    public void setParticipant(String participant) { mParticipant = participant; }

    public String getHours() { return mHours; }

    public void setHours(String hours) { mHours = hours; }

    public String getSubject() { return mSubject; }

    public void setSubject(String subject) { mSubject = subject; }

    public String getAvatar() { return mAvatar; }

    public void setAvatar(String avatar) { mAvatar = avatar; }
}
