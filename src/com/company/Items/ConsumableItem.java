package com.company.Items;

import com.company.Entities.Abstract.Creature;

public abstract class ConsumableItem extends Item {

    public abstract void use(Creature player);
}
