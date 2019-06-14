package com.company.Items;

import com.company.Entities.Creature;

public abstract class ConsumableItem extends Item {

    public abstract void use(Creature player);
}
