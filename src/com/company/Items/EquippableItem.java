package com.company.Items;


import com.company.Items.Storage.EquipmentSlots;

public class EquippableItem extends Item {

    private EquipmentSlots equipSlot;
    private int attackBonus;
    private int armourBonus;
    private String name;

    public EquippableItem(EquipmentSlots slot,String equipmentName , int attack, int armour){
        equipSlot = slot;
        attackBonus = attack;
        armourBonus = armour;
        name = equipmentName;
    }

    public EquipmentSlots getEquipSlot(){
        return equipSlot;
    }

    public int getAttack(){
        return attackBonus;
    }
    public int getArmour(){
        return armourBonus;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name + "(");
        if(getAttack() > 0){
            stringBuilder.append("Attack+" + getAttack() + " ,");
        }
        if(getArmour() > 0){
            stringBuilder.append("Armour+" + getArmour() + " ,");
        }
        stringBuilder.deleteCharAt(stringBuilder.length());
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
