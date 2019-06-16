package com.company.Map;

import com.company.Entities.Skeleton;
import com.company.Entities.Troll;
import com.company.Items.Potion;

import java.util.LinkedList;

public abstract class LevelFactory {

    private static LinkedList<LevelData> levels;

    public static LevelData getNextLevel(){
        if(levels == null) initialiseLevels();
        return levels.pop();
    }

    private static void initialiseLevels(){
        levels = new LinkedList<>();
        LevelData level = getNewLevelTemplate(5);
        level.addMonster(new Skeleton(1),5);
        level.addMonster(new Troll(1),1);
        level.addItem(new Potion(1),4);
        level.addBossRoom(new Skeleton(2));
        addLevel(level);

        level = getNewLevelTemplate(7);
        level.addMonster(new Skeleton(2),3);
        level.addMonster(new Skeleton(1),1);
        level.addMonster(new Troll(2),1);
        level.addItem(new Potion(2),4);
        level.addBossRoom(new Troll(3));
        addLevel(level);

        level = getNewLevelTemplate(12);
        level.addMonster(new Skeleton(1),1);
        level.addMonster(new Skeleton(4),3);
        level.addMonster(new Troll(2),2);
        level.addItem(new Potion(4),5);
        level.addBossRoom(new Skeleton(10));
        addLevel(level);
    }

    private static void addLevel(LevelData levelData){
        levels.add(levelData);
    }
    private static void addLevel(LevelData levelData, int index){
        levels.add(index,levelData);
    }

    private static LevelData getNewLevelTemplate(int noRooms){
        LevelData level = new LevelData();
        level.setLevelMap(MapGenerator.createNewMap(noRooms,LevelMap.getValidMoveDirections()));
        return level;
    }

}
