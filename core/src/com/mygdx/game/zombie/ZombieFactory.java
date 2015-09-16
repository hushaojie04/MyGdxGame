package com.mygdx.game.zombie;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.GameObjectActor.Card;
import com.mygdx.game.GameObjectActor.BaseZombie;

/**
 * Created by Administrator on 2015/9/16.
 */
public class ZombieFactory {
    public BaseZombie newZombie(BaseZombie.Kind kind, Stage stage, float x, float y) {
        if (kind == BaseZombie.Kind.Zombie) {
            return new Zombie(stage, x, y);
        }
        return null;
    }
}
