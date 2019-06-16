package com.company.Items.Storage;

import com.company.Items.EquippableItem;

import java.util.Map;


public interface Equipment {


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
    default int getEquipmentAttack(){
        int total = 0;
        for (EquippableItem e:getEquipmentInventory().values()) {
            total += e.getAttack();
        }
        return total;
    }
    default int getEquipmentArmour(){
        int total = 0;
        for(EquippableItem e: getEquipmentInventory().values()){
            total += e.getArmour();
        }
        return total;
    }
    default void printEquipment(){
        System.out.println("Equipment:------------------------------");
        for (EquipmentSlots e: EquipmentSlots.values()) {
            System.out.print(e.toString() + ": ");
            if(getEquipmentInventory().get(e) == null){
                System.out.print("None");
            }
            else{
                System.out.print(getEquipmentInventory().get(e).toString());
            }
            System.out.print("\n");
        }
        System.out.println("----------------------------------------");
    }

}
