package com.mygdx.game.Plant;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.GameObjectActor.Plant;
import com.mygdx.game.Utils.GifDecoder;
import com.mygdx.game.resource.Res;

/**
 * Created by Administrator on 2015/9/16.
 */
public class SunFlower extends Plant {
    public SunFlower(Stage stage, float x, float y) {
        super(stage, Res.sunFlowerAimation, x, y, false);
    }
}
