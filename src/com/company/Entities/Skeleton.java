package com.company.Entities;

public class Skeleton extends Creature{

    public Skeleton(int level){
        super("Skeleton",level,60,7,2,60,1,2,3,1);
    }

    @Override
    protected void levelUp() {
        increaseVitality(1);
        increaseDexterity(2);
        increaseStrength(2);
    }
}
