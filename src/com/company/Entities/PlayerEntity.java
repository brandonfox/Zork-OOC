package com.company.Entities;

import com.company.Items.Inventory.ItemContainer;
import com.company.Items.Item;

import java.util.ArrayList;
import java.util.List;

public class PlayerEntity extends Creature implements ItemContainer {

    private List<Item> inventory;

    private int experience;
    private int experienceToNextLevel = 200;

    public PlayerEntity(){
        super("Player",1,100,10,8,75,0,5,5,5);
        inventory = new ArrayList<>();
    }

    @Override
    protected void levelUp() {
        //TODO implement this
        //Let player decide what to spend skill points in
    }

    public void increaseExperience(int amount){
        System.out.println(this.toString() + " gained " + amount + " experience.");
        experience += amount;
        if(experience >= experienceToNextLevel){
            experience -= experienceToNextLevel;
            experienceToNextLevel += experienceToNextLevel/2;
        }
    }

    @Override
    public List<Item> getItemInventory() {
        return inventory;
    }

}
