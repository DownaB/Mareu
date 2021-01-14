package com.downa.maru.Model;

import android.widget.ImageView;

import androidx.annotation.DrawableRes;

public class Room {

    private String name;
    @DrawableRes
     private ImageView avatar;

    public Room(@DrawableRes ImageView avatar, String name ) {
        this.name = name;
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return name ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ImageView getAvatar() {
        return avatar;
    }

    public void setAvatar  (ImageView avatar) {
        this.avatar = avatar;
    }
}
