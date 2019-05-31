package com.company.Items;

import com.company.PlayerData;
import com.company.Map.Room;

public abstract class ConsumableItem extends Item {

    public abstract void use(PlayerData player, Room room);
}
