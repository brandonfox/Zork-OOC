package com.company.Map;

import com.company.CloneableObject;
import com.company.Entities.Monster;
import com.company.Items.Item;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LevelData {

    private LevelMap levelMap;
    private Map<CloneableObject,Integer> monsterMap;
    private Map<CloneableObject,Integer> itemDropMap;

    private int monsterChance = 70;

    private static final Random random = new Random();

    public LevelData(){
        monsterMap = new HashMap<>();
        itemDropMap = new HashMap<>();
    }

    public void setLevelMap(LevelMap map){
        levelMap = map;
        levelMap.addMoveDirection("north",new Point(0,1));
        levelMap.addMoveDirection("south",new Point(0,-1));
        levelMap.addMoveDirection("east",new Point(1,0));
        levelMap.addMoveDirection("west",new Point(-1,0));
        map.getCurrentRoom().removeMonster();
        map.getCurrentRoom().setRoomAsExplored();
    }

    public void setMonsterChance(int percentage){
        this.monsterChance = percentage;
    }

    public void addMonster(Monster monster, int spawnWeight){
        monsterMap.put(monster,spawnWeight);
    }

    public void addItem(Item item, int spawnWeight){itemDropMap.put(item,spawnWeight);}

    public Item getRandomItem(){
        return (Item)getRandomObject(itemDropMap);
    }

    /**
     * Get a random item designated for this level
     * @param percentage percentage chance of getting an item
     * @return Item or null
     */
    public Item getRandomItem(int percentage){
        if(random.nextInt(100) <= percentage){
            return getRandomItem();
        }
        return null;
    }

    private Object getRandomObject(Map<CloneableObject,Integer> objectMap){
        int totalWeights = getTotalWeights(objectMap);
        int objectInt = random.nextInt(totalWeights);
        for(CloneableObject o : objectMap.keySet()){
            objectInt -= objectMap.get(o);
            if(objectInt <= 0){
                return o.clone();
            }
        }
        return null;
    }

    private int getTotalWeights(Map<?,Integer> map){
        int total = 0;
        for(Integer i: map.values()){total += i;}
        return total;
    }

    public void displayCurrentRoomData(){
        levelMap.displayCurrentRoomData();
    }

    public Room getRoomAt(Point position){
        return levelMap.getRoomAt(position);
    }

    public void moveToRoom(Room room){
        if(!room.isExplored()){
            if(random.nextInt(100) <= monsterChance)
                room.setMonster((Monster)getRandomObject(monsterMap));
        }
        levelMap.setCurrentRoom(room);

    }

    public Room getCurrentRoom(){
        return levelMap.getCurrentRoom();
    }

    /**
     * Get a point in the direction 'direction'
     * @param direction The string to read
     * @return A point in the direction 'direction' or null if direction could not be read
     */
    public Point getPointInDirection(String direction){
        return levelMap.getPointInDirection(direction);
    }

}
