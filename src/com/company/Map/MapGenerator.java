package com.company.Map;


import java.awt.*;
import java.util.*;

public abstract class MapGenerator {

    private static Random random = new Random();

    /**
     * Create a random map layout
     * @param noRooms The number of rooms to generate
     * @return A pair consisting of the starting room and a hashmap of all rooms
     */
    public static LevelMap createNewMap(int noRooms,Map<String,Point> validDirections){
        Room startingRoom;
        HashMap<Point,Room> roomPositions = new HashMap<>();
        Point currentPoint = new Point(0,0);
        roomPositions.put(currentPoint,RoomFactory.createEmptyRoom(currentPoint));
        startingRoom = roomPositions.get(currentPoint);
        for(int i = 1; i < noRooms; i++){
            currentPoint = getAdjacentFreeRoom(currentPoint,roomPositions.keySet(),validDirections,true);
            roomPositions.put(currentPoint, RoomFactory.createRandomRoom(currentPoint));
        }
        return new LevelMap(startingRoom,roomPositions);
    }
    private static Point getAdjacentFreeRoom(Point currentPoint, Set<Point> takenPoints,Map<String,Point> validDirections, boolean deepSearch){
        //Get adjacent room.
        //First add free slots to a list
        ArrayList<Point> freeSlots = new ArrayList<>();
        for (Point p: validDirections.values()) {
            Point newPoint = new Point(currentPoint.x + p.x,currentPoint.y + p.y);
            if(!takenPoints.contains(newPoint)) freeSlots.add(newPoint);
        }

        //Return a direction at random if available
        if(freeSlots.size() > 0) {
            return freeSlots.get(random.nextInt(freeSlots.size()));
        }
        if(deepSearch) {
            //Else get a random other position
            for (Point p : takenPoints) {
                Point adjacent = getAdjacentFreeRoom(p,takenPoints,validDirections,false);
                if(adjacent != null)
                    return adjacent;
            }
        }
        //No room found return null
        //TODO consider making this an exception
        return null;
    }
    public static Room addRandomRoom(Map<Point,Room> mapToEdit){
        int roomNo = random.nextInt(mapToEdit.size());
        for (Point p: mapToEdit.keySet()) {
            roomNo--;
            if(roomNo <= 0) {
                Point freePoint = getAdjacentFreeRoom(p,mapToEdit.keySet(),LevelMap.getValidMoveDirections(),true);
                mapToEdit.put(freePoint,RoomFactory.createEmptyRoom(freePoint));
                return mapToEdit.get(freePoint);
            }
        }
        return null;
    }
}
