package com.company;


import java.util.HashMap;
import java.util.Map;

public class ProbabilityMap<T> extends HashMap<T,Integer>{

    private Map<T,Integer> map;

    public ProbabilityMap(){
        map = new HashMap<>();
    }

    public T getRandomItem(){
        int total = getTotalWeights();
        for (T t: map.keySet()) {
            total -= map.get(t);
            if(total <= 0){
                return t;
            }
        }
        //This is an error
        return null;
    }

    public void removeKey(T item){
        map.remove(item);
    }

    public void setKey(T item, int weight){
        map.put(item,weight);
    }

    private int getTotalWeights(){
        int total = 0;
        for (Integer i: map.values()) {
            total += i;
        }
        return total;
    }

}
