package Items;

import com.company.PlayerData;
import com.company.Room;

public abstract class Item {

    private int name;

    public abstract void use(PlayerData player, Room room);

}
