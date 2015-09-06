package com.mygdx.game.ViewHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Administrator on 2015/9/5.
 */
public class AreaButton {
    public final Rectangle mRectangle;
    private OnClickListener mOnClickListener;

    public AreaButton(float x, float y, float width, float height) {
        mRectangle = new Rectangle(x, y, width, height);
    }

    public void setOnClickListener(OnClickListener listener) {
        mOnClickListener = listener;
    }

    public void act(float delta) {
        if (mOnClickListener != null && isTouched()) {
            mOnClickListener.onClick(this.hashCode());
        }
    }

    protected boolean isTouched() {
        if (Gdx.input.isTouched()) {
            float x = Gdx.input.getX();
            float y = Gdx.graphics.getHeight() - Gdx.input.getY();
            if (mRectangle.contains(x, y)) {
                return true;
            }
        }
        return false;
    }
}
