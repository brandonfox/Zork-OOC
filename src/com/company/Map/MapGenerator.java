package com.company.Map;


import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

public abstract class MapGenerator {

    /**
     * Create a random map layout
     * @param noRooms The number of rooms to generate
     * @return A pair consisting of the starting room and a hashmap of all rooms
     */
    public static LevelMap createNewMap(int noRooms){
        Room startingRoom;
        HashMap<Point,Room> roomPositions = new HashMap<>();
        RoomFactory roomFactory = new RoomFactory();
        Point currentPoint = new Point(0,0);
        roomPositions.put(currentPoint,roomFactory.createEmptyRoom(currentPoint));
        startingRoom = roomPositions.get(currentPoint);
        for(int i = 1; i < noRooms; i++){
            currentPoint = getAdjacentFreeRoom(currentPoint,roomPositions.keySet(),true);
            roomPositions.put(currentPoint, roomFactory.createRandomRoom(currentPoint));
        }
        return new LevelMap(startingRoom,roomPositions);
    }
    private static Point getAdjacentFreeRoom(Point currentPoint, Set<Point> takenPoints, boolean deepSearch){
        //Get adjacent room.
        //First add free slots to a list
        ArrayList<Point> freeSlots = new ArrayList<>();
        Point north = new Point(currentPoint.x,currentPoint.y+1);
        Point east = new Point(currentPoint.x+1,currentPoint.y);
        Point south = new Point(currentPoint.x,currentPoint.y-1);
        Point west = new Point(currentPoint.x-1,currentPoint.y);
        if(!takenPoints.contains(north)) freeSlots.add(north);
        if(!takenPoints.contains(east)) freeSlots.add(east);
        if(!takenPoints.contains(south)) freeSlots.add(south);
        if(!takenPoints.contains(west)) freeSlots.add(west);

        //Return a direction at random if available
        if(freeSlots.size() > 0) {
            Random random = new Random();
            return freeSlots.get(random.nextInt(freeSlots.size()));
        }
        if(deepSearch) {
            //Else get a random other position
            for (Point p : takenPoints) {
                Point adjacent = getAdjacentFreeRoom(p,takenPoints,false);
                if(adjacent != null)
                    return adjacent;
            }
        }
        //No room found return null
        //TODO consider making this an exception
        return null;
    }
}
