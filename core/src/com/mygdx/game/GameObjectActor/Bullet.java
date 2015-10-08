package com.mygdx.game.GameObjectActor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.Utils.Log;
import com.mygdx.game.resource.Res;

/**
 * Created by Administrator on 2015/9/16.
 */
public class Bullet extends LifeObject {
    public Bullet(Stage stage, Animation move, Animation bom, float x, float y) {
        super(stage, move, x, y, true);
        Log.show("Bullet " + x + " " + y);
        setShadow(Res.getShadow(),0,0);
    }
    public enum Kind {
        SunFlower(50), Peashooter(150);
        public int cost;

        Kind(int cost) {
            this.cost = cost;
        }
    }
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        Log.show("finalize " + getClass().getName());
    }
}
