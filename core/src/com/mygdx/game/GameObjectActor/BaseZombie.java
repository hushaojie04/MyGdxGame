package com.mygdx.game.GameObjectActor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.Utils.Log;
import com.mygdx.game.World.World;
import com.mygdx.game.resource.Res;

/**
 * Created by Administrator on 2015/9/16.
 */
public class BaseZombie extends LifeObject {
    public boolean isDead;

    public BaseZombie(Stage stage, Animation animation, float x, float y) {
        super(stage, animation, x, y, true);
        Log.show("Zombie " + x + " " + y);
        isDead = false;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        Log.show("finalize " + getClass().getName());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (shadow != null) {
            batch.draw(shadow, shadowPaddingX + getX() - (shadow.getWidth() * World.ratioW - getWidth()) * 0.5f
                    , shadowPaddingY + getY() - getHeight() * 0.1f
                    , shadow.getWidth() * World.ratioW
                    , shadow.getHeight() * World.ratioH);
        }
        if (animation != null) {
            livingTime += Gdx.graphics.getDeltaTime();
            currentFrame = animation.getKeyFrame(livingTime, true);
            batch.draw(currentFrame, getX(), getY(), getWidth() * getScaleX(), getHeight() * getScaleY());
        }
    }


    public enum Kind {
        Zombie(5, 1);
        public int attack;
        public int defense;

        Kind(int attack, int defense) {
            this.attack = attack;
            this.defense = defense;
        }
    }
}
