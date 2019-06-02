package com.company.Map;

import java.awt.*;

public abstract class RoomFactory {

    public static Room createRandomRoom(Point position){
        Room room = new Room(position);
        //Add stuff here
        //TODO add monster and item to room (maybe both maybe not)
        return room;
    }
    public static Room createEmptyRoom(Point position){
        return new Room(position);
    }

}
