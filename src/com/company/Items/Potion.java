package com.company.Items;

import com.company.Entities.Creature;

public class Potion extends ConsumableItem {

    //TODO maybe split this into subpotion types (health potion, mana potion, strength potion etc)
    private int levelHealModifier = 20;
    private int heal;



    public Potion(int level){
        setAcceptedName("potion");
        if(level <= 0) setItemLevel(1);
        else
            setItemLevel(level);

        int levelIncrease = getItemLevel() * levelHealModifier;
        heal = 100 + levelIncrease + random.nextInt(levelIncrease * 2) - levelIncrease;
    }

    @Override
    public void use(Creature entity) {
        entity.heal(heal);
        entity.printHealth();
    }

    @Override
    public String toString() {
        return "Potion(" + heal + ")";
    }
}
