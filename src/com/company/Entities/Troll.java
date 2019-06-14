package com.company.Entities;

import com.company.Entities.Abstract.Monster;

public class Troll extends Monster {

    public Troll(int level){
        super("Troll",level,60,30,8,15,2,4,2,4,120);
    }

    @Override
    protected void levelUp() {
        increaseStrength(3);
        increaseDexterity(1);
        increaseVitality(5);
    }
}
