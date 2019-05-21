package com.company;

import java.awt.*;
import java.util.HashMap;

public class LevelMap {

    private Room currentRoom;
    private HashMap<Point,Room> roomMap;

    public LevelMap(Room startingRoom, HashMap<Point,Room> map){
        currentRoom = startingRoom;
        roomMap = map;
    }

    public void displayCurrentRoomData(){
        //TODO implement this
        System.out.println("You are in a room");
        System.out.println("There is nothing here");
        printRoomNeighbours(currentRoom);
    }
    private void printRoomNeighbours(Room room){
        //Print north
        if(roomMap.get(new Point(room.getRoomPosition().x,room.getRoomPosition().y+1))!= null) System.out.println("There is a room to the north");
        //Print east
        if(roomMap.get(new Point(room.getRoomPosition().x+1,room.getRoomPosition().y))!= null) System.out.println("There is a room to the east");
        //Print south
        if(roomMap.get(new Point(room.getRoomPosition().x,room.getRoomPosition().y-1))!= null) System.out.println("There is a room to the south");
        //Print west
        if(roomMap.get(new Point(room.getRoomPosition().x-1,room.getRoomPosition().y))!= null) System.out.println("There is a room to the west");
    }
}
