package com.company.Items.Inventory;

import com.company.Items.EquippableItem;
import com.company.Items.Item;

import java.util.Map;


public interface EquipmentContainer {


    Map<EquipmentSlots, EquippableItem> getEquipmentInventory();
    default void equipItem(EquippableItem item){
        getEquipmentInventory().put(item.getEquipSlot(),item);
    }
    default void deEquipItem(EquippableItem item){
        Map<EquipmentSlots,EquippableItem> inven = getEquipmentInventory();
        if(inven.get(item.getEquipSlot()) != item){
            System.out.println("You are not using " + item.toString());
        }
        else{
            inven.remove(item.getEquipSlot());
        }
    }

}
