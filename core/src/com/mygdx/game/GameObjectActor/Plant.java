package com.mygdx.game.GameObjectActor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.Utils.Log;
import com.mygdx.game.resource.Res;

/**
 * Created by Administrator on 2015/9/14.
 */
public class Plant extends LifeObject {
    public Plant(Stage stage, Animation animation, float x, float y, boolean isMove) {
        super(stage, animation, x, y, isMove);
        Log.show("Plant " + x + " " + y);
        setShadow(Res.getShadow());
    }

    public LifeObject produce() {
        return null;
    }
}
