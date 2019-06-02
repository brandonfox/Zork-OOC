package com.company.Map;

import com.company.Entities.Creature;
import com.company.Items.Inventory.ItemContainer;
import com.company.Items.Item;

import java.awt.*;
import java.util.Collection;
import java.util.HashSet;

public class Room implements ItemContainer {

    private Point roomPosition;
    private final Collection<Item> roomItems = new HashSet<>();
    private boolean explored = false;
    private Creature roomCreature;


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
    public Collection<Item> getItemInventory(){
        return roomItems;
    }
    public void setMonster(Creature c){roomCreature = c;}
    public Creature getMonster(){return roomCreature;}
}
