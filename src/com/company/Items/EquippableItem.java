package com.company.Items;


import com.company.Items.Inventory.EquipmentSlots;

public class EquippableItem extends Item {

    private EquipmentSlots equipSlot;

    public EquippableItem(EquipmentSlots slot){
        equipSlot = slot;
    }

    public EquipmentSlots getEquipSlot(){
        return equipSlot;
    }

}
