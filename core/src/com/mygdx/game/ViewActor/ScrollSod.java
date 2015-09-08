package com.mygdx.game.ViewActor;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.GameObjectActor.GameObject;
import com.mygdx.game.World;
import com.mygdx.game.resource.Res;


/**
 * Created by Administrator on 2015/9/8.
 */
public class ScrollSod extends Actor {
    private final Texture sod;
    private static final float DURATION = 1;
    private float percent = 0;
    TextureRegion region;
    Texture roll, rollCap;
    Texture growSoil;
    ScrollEndListener mScrollEndListener;

    public ScrollSod(float x, float y) {
        sod = Res.getSod1row();
        roll = Res.getSodRoll();
        rollCap = Res.getSodRollCap();
        growSoil = Res.getGrowSoil();

        setX(x);
        setY(y - sod.getHeight() / 2);
    }

    public void setScrollEndListener(ScrollEndListener mScrollEndListener) {
        this.mScrollEndListener = mScrollEndListener;
    }

    float time;

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        time += Gdx.graphics.getDeltaTime();
        if (percent <= 1f) {
            percent += 0.01f;
            region = new TextureRegion(sod, (int) (sod.getWidth() * percent), sod.getHeight());
            if (percent >= 1) {
                if (mScrollEndListener != null)
                    mScrollEndListener.end();
            }
        }
        float offsetX = region.getRegionWidth() * World.ratioW;
        batch.draw(region, getX(), getY(), offsetX, sod.getHeight() * World.ratioH);
        if (percent <= 1f) {
            float p = 1 - percent;
            batch.draw(roll, getX() + offsetX - roll.getWidth() * World.ratioW * 0.5f * p
                    , getY() + rollCap.getHeight() * World.ratioH * p * 0.13f
                    , roll.getWidth() * World.ratioW * p
                    , sod.getHeight() * World.ratioH);
            batch.draw(rollCap, getX() + offsetX - rollCap.getWidth() * World.ratioW * 0.5f * p, getY(), rollCap.getWidth() * World.ratioW * p, rollCap.getHeight() * World.ratioH * p);
            if (time < 0.1f) {
                batch.draw(growSoil, getX() + offsetX - growSoil.getWidth() * World.ratioW + 40
                        , getY() + sod.getHeight() * World.ratioH * 0.8f
                        , growSoil.getWidth() * World.ratioW
                        , growSoil.getHeight() * World.ratioH);
                batch.draw(growSoil, getX() + offsetX - growSoil.getWidth() * World.ratioW
                        , getY() + sod.getHeight() * World.ratioH * 0.4f
                        , growSoil.getWidth() * World.ratioW
                        , growSoil.getHeight() * World.ratioH);
                batch.draw(growSoil, getX() + offsetX - growSoil.getWidth() * World.ratioW + 40
                        , getY() + sod.getHeight() * World.ratioH * 0.2f
                        , growSoil.getWidth() * World.ratioW
                        , growSoil.getHeight() * World.ratioH);
            } else if (time < 0.2f) {
                batch.draw(growSoil, getX() + offsetX - growSoil.getWidth() * World.ratioW
                        , getY() + sod.getHeight() * World.ratioH * 0.8f
                        , growSoil.getWidth() * World.ratioW
                        , growSoil.getHeight() * World.ratioH);
                batch.draw(growSoil, getX() + offsetX - growSoil.getWidth() * World.ratioW + 40
                        , getY() + sod.getHeight() * World.ratioH * 0.4f
                        , growSoil.getWidth() * World.ratioW
                        , growSoil.getHeight() * World.ratioH);
                batch.draw(growSoil, getX() + offsetX - growSoil.getWidth() * World.ratioW
                        , getY() + sod.getHeight() * World.ratioH * 0.2f
                        , growSoil.getWidth() * World.ratioW
                        , growSoil.getHeight() * World.ratioH);
            } else if (time > 0.2f) {
                time = 0;
            }

        }
    }

    public interface ScrollEndListener {
        void end();
    }
}
