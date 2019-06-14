package com.company;


import java.util.HashMap;
import java.util.Random;

public class ProbabilityMap<T extends CloneableObject> extends HashMap<T,Integer>{

    private static final Random random = new Random();

    public T getRandomItem(){
        int x = random.nextInt(getTotalWeights());
        for (T t: this.keySet()) {
            x -= get(t);
            if(x <= 0){
                return (T)t.clone();
            }
        }
        //This is an error
        return null;
    }

    public void removeKey(CloneableObject item){
        remove(item);
    }

    public void setKey(T item, int weight){
        put(item,weight);
    }

    private int getTotalWeights(){
        int total = 0;
        for (Integer i: values()) {
            total += i;
        }
        return total;
    }

}
