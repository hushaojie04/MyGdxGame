package com.mygdx.game.ViewActor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.impl.OnClickListener;

/**
 * Created by Administrator on 2015/9/11.
 */
public class ClickHelper {
    public boolean act(Actor actor) {
        if (Gdx.input.isTouched()) {
            float y = Gdx.graphics.getHeight() - Gdx.input.getY();
            if (rectangle(actor).contains(Gdx.input.getX(), y)) {
                clicked();
                if (mOnClickListener != null) {
                    mOnClickListener.onClick(actor);
                }
                return true;
            }
        }
        return false;
    }

    public static Rectangle rectangle(Actor actor) {
        if (actor.getParent() != null) {
            return new Rectangle(actor.getX() + actor.getParent().getX(), actor.getY() + actor.getParent().getY(), actor.getWidth(), actor.getHeight());
        }
        return new Rectangle(actor.getX(), actor.getY(), actor.getWidth(), actor.getHeight());
    }

    protected OnClickListener mOnClickListener;

    public void setOnClickListener(OnClickListener mOnClickListener) {
        this.mOnClickListener = mOnClickListener;
    }

    protected void clicked() {

    }
}
