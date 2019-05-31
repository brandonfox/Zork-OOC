package com.company.Items;

public abstract class PotionFactory {

    //TODO create hashmap for random words and modifiers

    public static Potion getRandomPotion(int level){
        return new Potion(level);
    }
}
