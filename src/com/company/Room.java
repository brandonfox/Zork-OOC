package com.company;

import Items.Item;
import Items.ItemNotFoundException;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class Room {

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
    public Collection<Item> getItems(){
        return roomItems;
    }
    public void pickUpItem(PlayerData player, Item item){
        try{
            removeItem(item);
            player.placeInInventory(item);
        }catch(ItemNotFoundException ex){
            ex.printStackTrace();
        }
    }
    private void removeItem(Item item) throws ItemNotFoundException{
        if(!roomItems.contains(item))
            throw new ItemNotFoundException(this,item);
        roomItems.remove(item);
    }
}
