package com.company.Map;

import com.company.Items.Inventory.ItemContainer;
import com.company.Items.Item;
import com.company.Monsters.Monster;

import java.awt.*;
import java.util.Collection;
import java.util.HashSet;

public class Room implements ItemContainer {

    private Point roomPosition;
    private final Collection<Item> roomItems = new HashSet<>();
    private Monster roomMonster;

    public Room(Point p){
        roomPosition = p;
    }
    public Point getRoomPosition(){
        return roomPosition;
    }
    public boolean hasItem(){
        return getItemInventory().size() > 0;
    }
    public boolean hasMonster(){return roomMonster != null;}
    public Monster getRoomMonster(){return roomMonster;}
    public Collection<Item> getItemInventory(){
        return roomItems;
    }
}
