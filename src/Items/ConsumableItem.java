package Items;

import com.company.PlayerData;
import com.company.Room;

public abstract class ConsumableItem extends Item {

    public abstract void use(PlayerData player, Room room);
}
