package com.company.Entities;

public class Skeleton extends Monster{

    public Skeleton(int level){
        super("Skeleton",level,40,7,2,60,1,2,3,1,15);
    }

    @Override
    protected void levelUp() {
        increaseVitality(1);
        increaseDexterity(2);
        increaseStrength(2);
    }
}
