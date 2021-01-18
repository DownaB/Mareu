package com.downa.maru.Model;

import androidx.annotation.DrawableRes;

public class Room {

    private String name;
    @DrawableRes
    private int avatar;

    public Room(@DrawableRes int avatar, String name) {
        this.name = name;
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }
}
