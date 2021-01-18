package com.downa.maru.Controller;

import android.graphics.drawable.Drawable;

import com.downa.maru.Model.Room;
import com.downa.maru.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class RoomGenerator {

    public static List<Room> RoomList = Arrays.asList(

            new Room(R.drawable.avatar_blue, "Aphrodite"),
            new Room(R.drawable.avatar_brown, "Apollon"),
            new Room(R.drawable.avatar_darkpurple, "Poseidon"),
            new Room(R.drawable.avatar_green, "Ares"),
            new Room(R.drawable.avatar_pink, "Artemis"),
            new Room(R.drawable.avatar_red, "Hera"),
            new Room(R.drawable.avatar_yellow, "Hermes"),
            new Room(R.drawable.avatar_purple, "Zeus"),
            new Room(R.drawable.avatar_grey, "Hadès"),
            new Room(R.drawable.avatar_darkblue, "Titan")

    );


    public static List<Room> generateRoom() {
        return new ArrayList<>(RoomList);
    }

}
