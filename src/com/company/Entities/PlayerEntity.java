package com.company.Entities;

import com.company.Commands.CommandParser;
import com.company.Entities.Abstract.Creature;
import com.company.Items.ConsumableItem;
import com.company.Items.EquippableItem;
import com.company.Items.Storage.Equipment;
import com.company.Items.Storage.EquipmentSlots;
import com.company.Items.Storage.ItemContainer;
import com.company.Items.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerEntity extends Creature implements ItemContainer, Equipment {

    private List<Item> inventory;
    private Map<EquipmentSlots,EquippableItem> equipment;

    private int experience;
    private int experienceToNextLevel = 200;

    public PlayerEntity(){
        super("Player",1,100,10,8,75,0,5,5,5);
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
        if(inventory == null) inventory = new ArrayList<>();
        return inventory;
    }

    @Override
    public Map<EquipmentSlots, EquippableItem> getEquipmentInventory() {
        if(equipment == null) equipment = new HashMap<>();
        return equipment;
    }
    public void printData(){
        System.out.println("You are a level " + level + " player");
        printHealth();
        printEquipment();
    }
    public void useItem(String item){
        Item itemToUse = CommandParser.parseItemCommand(item,getItemInventory());
        try{
            System.out.println("You use the " + itemToUse);
            ConsumableItem cItem = (ConsumableItem)itemToUse;
            cItem.use(this);
            removeItem(cItem);
        }
        catch(Exception e){
            System.out.println("You cant use the " + itemToUse);
        }
    }

    @Override
    public int getAttack() {
        int attack = super.getAttack();
        attack += getEquipmentAttack();
        return attack;
    }

    @Override
    public int getDefence() {
        int def = super.getDefence();
        def += getEquipmentArmour();
        return def;
    }
}
