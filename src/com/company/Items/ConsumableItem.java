package com.company.Items;

import com.company.Entities.Creature;
import com.company.Map.Room;

public abstract class ConsumableItem extends Item {

    public abstract void use(Creature player, Room room);
}
