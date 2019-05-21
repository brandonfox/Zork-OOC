package com.company;

import java.awt.*;

public abstract class RoomFactory {

    public static Room createRandomRoom(Point position){
        //TODO place random stuff in room
        return new Room(position);
    }
    public static Room createEmptyRoom(Point position){
        return new Room(position);
    }

}
