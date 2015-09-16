package com.mygdx.game.plantBullet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.GameObjectActor.LifeObject;
import com.mygdx.game.GameObjectActor.Plant;
import com.mygdx.game.Utils.GifDecoder;

/**
 * Created by Administrator on 2015/9/16.
 */
public class PeashooterBullet extends Plant {
    public PeashooterBullet(Stage stage, float x, float y) {
        super(stage, GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("image/plants/Peashooter.gif").read()), x, y, false);
    }

}
