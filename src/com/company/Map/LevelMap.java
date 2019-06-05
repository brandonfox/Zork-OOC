package com.company.Map;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class LevelMap {

    private Room currentRoom;
    private Map<Point,Room> roomMap;
    private Map<String,Point> moveDirections;

    public LevelMap(Room startingRoom, Map<Point,Room> map){
        currentRoom = startingRoom;
        roomMap = map;
        moveDirections = new HashMap<>();
    }

    public void addMoveDirection(String moveDir, Point moveData){
        moveDirections.put(moveDir,moveData);
    }

    public void displayCurrentRoomData(){
        System.out.println("You are in a room");
        System.out.println("There is nothing here");
        printRoomNeighbours(currentRoom);
    }
    private void printRoomNeighbours(Room room){
        //TODO change this to use hashmap
        //Print north
        Room northRoom = getRoomAt(new Point(room.getRoomPosition().x,room.getRoomPosition().y+1));
        printHasRoomInDirection(northRoom,"north");
        //Print east
        Room eastRoom = getRoomAt(new Point(room.getRoomPosition().x+1,room.getRoomPosition().y));
        printHasRoomInDirection(eastRoom, "east");
        //Print south
        Room southRoom = getRoomAt(new Point(room.getRoomPosition().x,room.getRoomPosition().y-1));
        printHasRoomInDirection(southRoom, "south");
        //Print west
        Room westRoom = getRoomAt(new Point(room.getRoomPosition().x-1,room.getRoomPosition().y));
        printHasRoomInDirection(westRoom, "west");
    }
    public Room getRoomAt(Point position){
        return roomMap.get(position);
    }
    public Room getCurrentRoom(){
        return currentRoom;
    }
    public void setCurrentRoom(Room room){
        currentRoom = room;
        room.setRoomAsExplored();
    }
    private void printHasRoomInDirection(Room room, String direction){
        if(room != null) {
            if (room.isExplored()) {
                System.out.println("There is a room to the " + direction);
            } else {
                System.out.println("There is an unexplored room to the " + direction);
            }
        }
    }
    /**
     * Get a point in the direction 'direction'
     * @param direction The string to read
     * @return A point in the direction 'direction' or null if direction could not be read
     */
    public Point getPointInDirection(String direction){
        for (String s: moveDirections.keySet()) {
            if(s.contains(direction)){
                return new Point(currentRoom.getRoomPosition().x + moveDirections.get(s).x,currentRoom.getRoomPosition().y + moveDirections.get(s).y);
            }
        }
        return null;
    }
}
