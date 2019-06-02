package com.company.Entities;

public class Troll extends Creature {

    public Troll(int level){
        super("Troll",level,130,30,8,45,2,4,2,4);
    }

    @Override
    protected void levelUp() {
        increaseStrength(3);
        increaseDexterity(1);
        increaseVitality(5);
    }
}
