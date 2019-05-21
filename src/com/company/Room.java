package com.company;

import java.awt.*;

public class Room {

    private Point roomPosition;

    public Room(Point p){
        roomPosition = p;
    }
    public Point getRoomPosition(){
        return roomPosition;
    }
}
