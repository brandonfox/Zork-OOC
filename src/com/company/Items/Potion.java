package com.company.Items;

import com.company.PlayerData;
import com.company.Map.Room;

public class Potion extends ConsumableItem {

    //TODO maybe split this into subpotion types (health potion, mana potion, strength potion etc)
    private int levelHealModifier = 20;
    private int heal;



    public Potion(int level){
        setAcceptedName("potion");
        setItemLevel(level);
        int levelIncrease = getItemLevel() * levelHealModifier;
        heal = 100 + levelIncrease + random.nextInt(levelIncrease * 2) - levelIncrease;
    }

    @Override
    public void use(PlayerData player, Room room) {
        player.heal(heal);
    }
}