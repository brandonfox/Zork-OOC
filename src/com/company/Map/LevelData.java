package com.company.Map;

import com.company.Entities.Monster;
import com.company.Items.Inventory.ItemContainer;
import com.company.Items.Item;
import com.company.ProbabilityMap;

import java.awt.*;
import java.util.Random;

public class LevelData {

    private LevelMap levelMap;
    private ProbabilityMap<Monster> monsterMap;
    private ProbabilityMap<Item> itemDropMap;

    private int monsterChance = 70;

    private static final Random random = new Random();

    public LevelData(){
        monsterMap = new ProbabilityMap<>();
        itemDropMap = new ProbabilityMap<>();
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
        monsterMap.setKey(monster,spawnWeight);
    }

    public void addItem(Item item, int spawnWeight){itemDropMap.put(item,spawnWeight);}

    public Item getRandomItem(){
        return itemDropMap.getRandomItem();
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
                room.setMonster(monsterMap.getRandomItem());
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

    public void defeatedMonster(ItemContainer playerInventory){
        Monster curMonster = getCurrentRoom().getMonster();
        Item itemReward = curMonster.getRandomDrop(false);
        if(itemReward != null){
            System.out.println(curMonster.toString() + " dropped a " + itemReward.toString());
            playerInventory.collectItem(itemReward);
        }
        getCurrentRoom().removeMonster();
    }

}
