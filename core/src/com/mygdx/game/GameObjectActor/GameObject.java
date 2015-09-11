package com.mygdx.game.GameObjectActor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.game.Utils.Log;
import com.mygdx.game.World;
import com.mygdx.game.impl.OnClickListener;

/**
 * Created by Administrator on 2015/9/7.
 */
public class GameObject extends Actor implements Disposable {
    private Texture background;
    private Animation animation;
    private float livingTime = 0;
    private TextureRegion currentFrame;

    public GameObject(Stage stage, Texture texture, float x, float y, float width, float height) {
        super();
        init(stage, null, texture, x, y, width, height);
    }

    public GameObject(Stage stage, Animation animation, float x, float y) {
        super();
        init(stage, animation, null, x, y,
                animation.getKeyFrames()[0].getRegionWidth() * World.ratioW,
                animation.getKeyFrames()[0].getRegionHeight() * World.ratioH);
    }

    public void init(Stage stage, Animation animation, Texture texture, float x, float y, float width, float height) {
        background = texture;
        this.animation = animation;
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
        stage.addActor(this);
    }

    public Rectangle rectangle() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
//        Log.show("draw " + getScaleX() + " " + getScaleY() + " " + getX());
        if (background != null)
            batch.draw(background, getX(), getY(), getWidth(), getHeight());
        if (animation != null) {
            livingTime += Gdx.graphics.getDeltaTime();
            currentFrame = animation.getKeyFrame(livingTime, true);
            batch.draw(currentFrame, getX(), getY(), getWidth() * getScaleX(), getHeight() * getScaleY());
        }
    }

    private float objectLifeTime = -1f;
    private boolean startLifeTimeMode = false;
    private boolean enableTouchMode = false;
    private OnClickListener mOnClickListener;

    public void setLifeTime(boolean startLifeTimeMode, float objectLifeTime) {
        this.objectLifeTime = objectLifeTime;
        this.startLifeTimeMode = startLifeTimeMode;
    }

    public void setTouchMode(boolean enable) {
        enableTouchMode = enable;
    }

    public void setOnClickListener(OnClickListener listener) {
        enableTouchMode = true;
        mOnClickListener = listener;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (startLifeTimeMode) {
            if (livingTime > objectLifeTime) {
                getParent().removeActor(this);
            }
        }
        if (enableTouchMode) {
            if (Gdx.input.isTouched()) {
                float y = Gdx.graphics.getHeight() - Gdx.input.getY();
                if (rectangle().contains(Gdx.input.getX(), y)) {
                    if (mOnClickListener != null) {
                        mOnClickListener.onClick(this);
                    }
                }
            }
        }
    }


    public float getLivingTime() {
        return livingTime;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        Log.show("finalize " + hashCode());
    }

    @Override
    public void dispose() {
        background = null;
        currentFrame = null;
        animation = null;
    }

}
