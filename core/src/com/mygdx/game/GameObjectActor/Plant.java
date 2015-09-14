package com.mygdx.game.GameObjectActor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by Administrator on 2015/9/14.
 */
public class Plant extends LifeObject {
    public Plant(Stage stage, Animation animation, float x, float y) {
        super(stage, animation, x, y, false);
    }
}
