package com.company.Map;

import com.company.Entities.Abstract.Creature;

import java.util.HashMap;
import java.util.Map;

public class FloorCreatureData {

    Map<Creature,Integer> monsterMap;

    public FloorCreatureData(){
        monsterMap = new HashMap<>();
    }

    public void addCreature(Creature c, int weight){
        monsterMap.put(c,weight);
    }

    private int getTotalWeights(){
        int total = 0;
        for (Integer i: monsterMap.values()) {
            total += i;
        }
        return total;
    }
}
