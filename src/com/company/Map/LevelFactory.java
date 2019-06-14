package com.company.Map;

import com.company.Entities.Skeleton;
import com.company.Entities.Troll;
import com.company.Items.Potion;

public abstract class LevelFactory {

    public static LevelData getLevel1(){
        LevelData level = new LevelData();
        level.setLevelMap(MapGenerator.createNewMap(15));
        level.addMonster(new Skeleton(1),5);
        level.addMonster(new Troll(1),1);
        level.addItem(new Potion(1),4);
        return level;
    }

}
