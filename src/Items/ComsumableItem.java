package Items;

import com.company.PlayerData;
import com.company.Room;

public abstract class ComsumableItem extends Item {

    public abstract void use(PlayerData player, Room room);
}
