package Items;

import com.company.PlayerData;
import com.company.Room;

public class Potion extends Item{

    private int heal;

    public Potion(int healAmount){
        heal = healAmount;
    }

    @Override
    public void use(PlayerData player, Room room) {
        player.heal(heal);
    }
}
