package com.company.Entities;

import com.company.Entities.Abstract.Monster;
import com.company.Items.EquippableItem;
import com.company.Items.Storage.EquipmentSlots;

public class Skeleton extends Monster {

    public Skeleton(int level){
        super("Skeleton",level,40,7,2,60,1,2,3,1,15);
    }

    @Override
    protected void levelUp() {
        increaseVitality(1);
        increaseDexterity(2);
        increaseStrength(2);
    }

    @Override
    protected void addExtraDrops() {
        addItemDrop(new EquippableItem(EquipmentSlots.hands,"Sword",10,0),1);
    }
}
