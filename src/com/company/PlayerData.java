package com.company;

import com.company.Items.EquippableItem;
import com.company.Items.Inventory.EquipmentContainer;
import com.company.Items.Inventory.EquipmentSlots;
import com.company.Items.Inventory.ItemContainer;
import com.company.Items.Item;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class PlayerData implements ItemContainer {
    private int health;
    private int maxHealth;

    private Collection<Item> inventory;

    public PlayerData(){
        inventory = new HashSet<>();
        maxHealth = 100;
        health = maxHealth;
    }

    public void heal(int amount){
        health += amount;
        if(health > maxHealth)
            health = maxHealth;
    }

    @Override
    public Collection<Item> getItemInventory() {
        return inventory;
    }

}
