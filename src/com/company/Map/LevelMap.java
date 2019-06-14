package com.company.Map;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class LevelMap {

    private Room currentRoom;
    private Map<Point,Room> roomMap;
    private static Map<String,Point> moveDirections;

    public LevelMap(Room startingRoom, Map<Point,Room> map){
        currentRoom = startingRoom;
        roomMap = map;

    }

    public static Map<String,Point> getValidMoveDirections(){
        if(moveDirections == null){
            moveDirections = new HashMap<>();
            addMoveDirection("north",new Point(0,1));
            addMoveDirection("south",new Point(0,-1));
            addMoveDirection("east",new Point(1,0));
            addMoveDirection("west",new Point(-1,0));
        }
        return moveDirections;
    }

    private static void addMoveDirection(String moveDir, Point moveData){
        moveDirections.put(moveDir,moveData);
    }

    public void displayCurrentRoomData(){
        System.out.println("You are in a room");
        System.out.println("There is nothing here");
        printRoomNeighbours();
    }
    private void printRoomNeighbours(){
        for(String dir: moveDirections.keySet()){
            printHasRoomInDirection(dir);
        }
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
    private void printHasRoomInDirection(String direction){
        Room room = getRoomAt(getPointInDirection(direction));
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

    /**
     * Create a random room adjacent to another room on the map
     * @return the newly created room
     */
    public Room addRandomRoom(){
        //Just add a new room next to the last room in the list
        //If it is not the current room
        return MapGenerator.addRandomRoom(roomMap);
    }
}
