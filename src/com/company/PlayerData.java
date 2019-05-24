package com.company;

import Items.Item;

import java.util.ArrayList;
import java.util.List;

public class PlayerData {
    private int health;
    private int maxHealth;

    private List<Item> inventory;

    public PlayerData(){
        inventory = new ArrayList<>();
        maxHealth = 100;
        health = maxHealth;
    }

    public void heal(int amount){
        health += amount;
        if(health > maxHealth)
            health = maxHealth;
    }

    public void placeInInventory(Item item){
        inventory.add(item);
    }


}
