package com.company.Map;

import com.company.Entities.Skeleton;
import com.company.Entities.Troll;
import com.company.Items.Potion;

import java.util.HashMap;
import java.util.Map;

public abstract class LevelFactory {

    private static Map<Integer,LevelData> levels;

    public static LevelData getLevel(int level){
        if(levels == null) initialiseLevels();
        return levels.get(level);
    }

    private static void initialiseLevels(){
        levels = new HashMap<>();
        LevelData level = new LevelData();
        level.setLevelMap(MapGenerator.createNewMap(5,LevelMap.getValidMoveDirections()));
        level.addMonster(new Skeleton(1),5);
        level.addMonster(new Troll(1),1);
        level.addItem(new Potion(1),4);
        level.addBossRoom(new Skeleton(2));
        levels.put(1,level);

        level = new LevelData();
        level.setLevelMap(MapGenerator.createNewMap(7,LevelMap.getValidMoveDirections()));
        level.addMonster(new Skeleton(2),3);
        level.addMonster(new Skeleton(1),1);
        level.addMonster(new Troll(2),1);
        level.addItem(new Potion(2),4);
        level.addBossRoom(new Troll(3));
        levels.put(2,level);
    }

}
