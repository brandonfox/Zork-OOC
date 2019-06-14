package com.company.Combat;

import com.company.Entities.Abstract.Monster;
import com.company.Entities.PlayerEntity;

public class BossBattle extends Battle {

    public BossBattle(Monster... monsters){
        super(monsters);
    }

    @Override
    public int startBattle(PlayerEntity player) {
        int code = super.startBattle(player);
        if(code == 2){ //Boss was defeated
            return 3; //Boss defeated code
        }
        return code;
    }
}
