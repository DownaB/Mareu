package com.downa.maru.Model;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.widget.ImageView;

public class Room {

    private String name;
    private ImageView avatar;

    public Room(ImageView avatar, String name ) {
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

    public void ImageView (ImageView avatar) {
        this.avatar = avatar;
    }
}
