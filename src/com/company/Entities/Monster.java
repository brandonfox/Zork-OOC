package com.company.Entities;


public abstract class Monster extends Creature {

    public Monster(String creatureName, int level,int baseHealth,int baseDamage,int randomAttackDeviation,int baseAccuracy, int armour,int strength, int dexterity, int vitality, int baseExp){
        super(creatureName,level,baseHealth,baseDamage,randomAttackDeviation,baseAccuracy,armour,strength,dexterity,vitality);
        this.experienceValue = baseExp + Math.round(baseExp * level * 0.3f) + random.nextInt(baseExp/3);
    }

    private int experienceValue;
    public int getExperienceValue(){return experienceValue;}

}
