package com.company;

public class PlayerData {
    //TODO implement player
    private int health;
    private int maxHealth;

    public PlayerData(){
        maxHealth = 100;
        health = maxHealth;
    }

    public void heal(int amount){
        health += amount;
        if(health > maxHealth)
            health = maxHealth;
    }

}