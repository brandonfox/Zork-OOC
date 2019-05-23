package com.company;

import Items.Item;

import java.awt.*;

public class Room {

    private Point roomPosition;
    private Item roomItem;
    private Monster roomMonster;

    public Room(Point p){
        roomPosition = p;
    }
    public Point getRoomPosition(){
        return roomPosition;
    }
}
