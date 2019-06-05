package com.company.Map;

import com.company.Entities.Monster;
import com.company.Items.Inventory.ItemContainer;
import com.company.Items.Item;

import java.awt.*;
import java.util.ArrayList;

public class Room implements ItemContainer {

    private Point roomPosition;
    private java.util.List<Item> roomItems = new ArrayList<>();
    private boolean explored = false;
    private Monster roomCreature;


    public Room(Point p){
        roomPosition = p;
    }
    public Point getRoomPosition(){
        return roomPosition;
    }
    public boolean hasItem(){
        return getItemInventory().size() > 0;
    }
    public boolean hasMonster(){return roomCreature != null;}
    public void setRoomAsExplored(){explored = true;}
    public boolean isExplored(){return explored;}
    public java.util.List<Item> getItemInventory(){
        return roomItems;
    }
    public void setMonster(Monster c){roomCreature = c;}
    public Monster getMonster(){return roomCreature;}
    public void removeMonster(){roomCreature = null;}
}
