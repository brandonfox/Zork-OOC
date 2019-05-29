package com.company;

import Items.Item;
import Items.ItemContainer;

import java.util.ArrayList;
import java.util.Collection;

public class PlayerData implements ItemContainer {
    private int health;
    private int maxHealth;

    private Collection<Item> inventory;

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

    @Override
    public Collection<Item> getInventory() {
        return inventory;
    }
}
