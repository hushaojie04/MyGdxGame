package com.mygdx.game.zombie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.GameObjectActor.BaseZombie;
import com.mygdx.game.Utils.GifDecoder;
import com.mygdx.game.Utils.Log;
import com.mygdx.game.resource.Res;

/**
 * Created by Administrator on 2015/9/16.
 */
public class Zombie extends BaseZombie {
    public Zombie(Stage stage, float x, float y) {
        super(stage, GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("image/Zombie/Zombie.gif").read()), x, y);
        Log.show("Bullet " + x + " " + y);
        setShadow(Res.getShadow());
    }
}
