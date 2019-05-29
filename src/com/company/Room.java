package com.company;

import Items.Item;
import Items.ItemContainer;
import Items.ItemNotFoundException;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class Room implements ItemContainer {

    private Point roomPosition;
    private final Collection<Item> roomItems = new ArrayList<>();
    private Monster roomMonster;

    public Room(Point p){
        roomPosition = p;
    }
    public Point getRoomPosition(){
        return roomPosition;
    }
    public boolean hasItem(){
        return roomItems.size() > 0;
    }
    public Collection<Item> getInventory(){
        return roomItems;
    }
}
