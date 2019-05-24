package Items;

import com.company.PlayerData;
import com.company.Room;

public class Potion extends ConsumableItem {

    //TODO maybe split this into subpotion types (health potion, mana potion, strength potion etc)
    private int heal;

    public Potion(int healAmount){
        setAcceptedName("potion");
        heal = healAmount;
    }

    @Override
    public void use(PlayerData player, Room room) {
        player.heal(heal);
    }
}
