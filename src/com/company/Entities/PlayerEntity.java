package com.company.Entities;

import com.company.Items.Inventory.ItemContainer;
import com.company.Items.Item;

import java.util.Collection;
import java.util.HashSet;

public class PlayerEntity extends Creature implements ItemContainer {

    private Collection<Item> inventory;

    public PlayerEntity(){
        super("Player",1,100,10,8,75,0,5,5,5);
        inventory = new HashSet<>();
    }

    @Override
    protected void levelUp() {
        //TODO implement this
        //Let player decide what to spend skill points in
    }

    @Override
    public Collection<Item> getItemInventory() {
        return inventory;
    }

}
