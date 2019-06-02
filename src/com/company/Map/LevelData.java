package com.company.Map;

import com.company.Entities.Creature;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LevelData {

    private LevelMap levelMap;
    private Map<Creature,Integer> monsterMap;

    private int monsterChance = 70;

    private static final Random random = new Random();

    public LevelData(){
        monsterMap = new HashMap<>();
    }

    public void setLevelMap(LevelMap map){
        levelMap = map;
    }

    public void setMonsterChance(int percentage){
        this.monsterChance = percentage;
    }

    public void addMonster(Creature monster, int spawnWeight){
        monsterMap.put(monster,spawnWeight);
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
                room.setMonster(getRandomMonster());
        }
        levelMap.setCurrentRoom(room);

    }

    public Room getCurrentRoom(){
        return levelMap.getCurrentRoom();
    }

    private Creature getRandomMonster(){
        int totalWeights = 0;
        for(Integer i: monsterMap.values()){ totalWeights += i;}
        int monster = random.nextInt(totalWeights);
        for(Creature c: monsterMap.keySet()){
            monster -= monsterMap.get(c);
            if(monster <= 0){
                return c;
            }
        }
        //This should never happen
        return null;
    }

}
