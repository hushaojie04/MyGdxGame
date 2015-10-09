package com.mygdx.game.zombie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Rectangle;
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
        super(stage, Res.zombieAimation, x, y);
        Log.show("Zombie " + x + " " + y);
        setShadow(Res.getShadow(), 60, 10);
        setSpeedX(-1);
    }

    public void stopAttack() {
        animation = Res.zombieAimation;
    }

    public void startAttack() {
        animation = Res.zombieAttackAimation;
    }

    public Rectangle rectangle() {
        return new Rectangle(getX() + getWidth() * 0.7f, getY(), 1, getHeight());
    }
}
