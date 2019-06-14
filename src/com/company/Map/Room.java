package com.company.Map;

import com.company.Combat.Battle;
import com.company.Combat.BossBattle;
import com.company.Entities.Abstract.Monster;
import com.company.Items.Storage.ItemContainer;
import com.company.Items.Item;

import java.awt.*;
import java.util.ArrayList;

public class Room implements ItemContainer {

    private Point roomPosition;
    private java.util.List<Item> roomItems = new ArrayList<>();
    private boolean explored = false;
    private Monster roomCreature;

    private boolean isBossRoom = false;


    public Room(Point p){
        roomPosition = p;
    }
    public boolean isBossRoom(){return isBossRoom;}
    public void makeBossRoom(Monster bossMonster){
        isBossRoom = true;
        roomCreature = bossMonster;
    }
    public Point getRoomPosition(){
        return roomPosition;
    }
    public boolean hasItem(){
        return getItemInventory().size() > 0;
    }
    public Battle getRoomBattle(){
        if(!hasMonster()){return null;}
        else if(isBossRoom){return new BossBattle(roomCreature);}
        else{
            return new Battle(roomCreature);
        }
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
