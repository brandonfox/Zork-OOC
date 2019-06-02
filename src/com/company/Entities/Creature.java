package com.company.Entities;

import java.util.Random;

public abstract class Creature {

    private int currentHealth;
    private int maxHealth;
    private int baseHealth;

    private String name;

    //Influences raw damage
    protected int strength = 0;
    //Influences dodges and speed
    protected int dexterity = 0;
    //Influences health
    protected int vitality = 0;

    protected int armour;

    private static final Random random = new Random();

    private int baseAccuracy;
    private int baseDamage;
    private int randomAttackDeviation;

    public Creature(String creatureName, int level,int baseHealth,int baseDamage,int randomAttackDeviation,int baseAccuracy, int armour,int strength, int dexterity, int vitality){
        name = creatureName;
        this.baseHealth = baseHealth;
        increaseStrength(strength);
        increaseDexterity(dexterity);
        increaseVitality(vitality);
        this.baseDamage = baseDamage;
        this.randomAttackDeviation = randomAttackDeviation;
        this.baseAccuracy = baseAccuracy;
        this.armour = armour;
        for(int i = 0; i < level; i++){levelUp();}
        currentHealth = maxHealth;
    }


    public int getCurrentHealth(){
        return currentHealth;
    }

    public void heal(int amount){
        currentHealth += amount;
        if(currentHealth > maxHealth)
            currentHealth = maxHealth;
    }

    public int getHitAccuracy(){
        return baseAccuracy + Math.round(dexterity * 1.6f);
    }
    public int getHitAvoidance(){
        return Math.round(dexterity * 0.6f);
    }
    public int getDefence(){
        return armour;
    }
    public int getAttack(){
        return baseDamage + Math.round(strength * 1.3f) + random.nextInt(randomAttackDeviation) - randomAttackDeviation/2;
    }
    public void takeDamage(int damage){
        currentHealth -= damage;
        if(currentHealth < 0)
            currentHealth = 0;
    }
    protected void increaseVitality(int amount){
        vitality += amount;
        maxHealth = baseHealth + vitality * 7;
    }
    protected void increaseStrength(int amount){
        strength += amount;
    }
    protected void increaseDexterity(int amount){
        dexterity += amount;
    }

    protected abstract void levelUp();

    @Override
    public String toString() {
        return name;
    }
}
