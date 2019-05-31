package com.company.Map;

import com.company.Items.Item;

import java.awt.*;
import java.util.Map;
import java.util.Random;

public class RoomFactory {

    //TODO add hashmap for item generation probabilities
    private Map<Item,Integer> itemsToGenerate;
    private static Random random = new Random();
    //Probability out of 100
    private static final int monsterSpawnChance = 80;


    public Room createRandomRoom(Point position){
        Room room = new Room(position);
        //Add stuff here
        //TODO add monster and item to room (maybe both maybe not)
        return room;
    }
    public Room createEmptyRoom(Point position){
        return new Room(position);
    }

}
