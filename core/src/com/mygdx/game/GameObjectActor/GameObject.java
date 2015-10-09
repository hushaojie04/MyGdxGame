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
import com.mygdx.game.ViewActor.ClickHelper;
import com.mygdx.game.World.World;
import com.mygdx.game.impl.OnClickListener;
import com.sun.prism.shader.Solid_Color_AlphaTest_Loader;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/9/7.
 */
public class GameObject extends Actor implements Disposable {
    protected Texture background, shadow;
    protected Animation animation;
    protected float livingTime = 0;
    protected TextureRegion currentFrame;
    protected ClickHelper mClickHelper;
    protected float shadowPaddingX, shadowPaddingY;

    public GameObject() {
        super();
        mClickHelper = new ClickHelper() {
            @Override
            protected void clicked() {
                GameObject.this.clicked();
            }
        };
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    public ClickHelper getClickHelper() {
        return mClickHelper;
    }

    public GameObject(Stage stage, Texture texture, float x, float y, float width, float height) {
        this();
        init(stage, null, texture, x, y, width, height);
    }

    public GameObject(Stage stage, Animation animation, float x, float y) {
        this();
        Log.show(getClass().toString() + " " + animation.getKeyFrames()[0].getRegionWidth() * World.ratioW);
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

    public void setShadow(Texture shadow, float paddingX, float paddingY) {
        this.shadow = shadow;
        shadowPaddingX = paddingX;
        shadowPaddingY = paddingY;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
//        Log.show("draw " + getScaleX() + " " + getScaleY() + " " + getX());
        if (shadow != null) {
            batch.draw(shadow, shadowPaddingX + getX() - (shadow.getWidth() * World.ratioW - getWidth()) * 0.5f
                    , shadowPaddingY + getY() - getHeight() * 0.1f
                    , shadow.getWidth() * World.ratioW
                    , shadow.getHeight() * World.ratioH);
        }
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

    public void setLifeTime(boolean startLifeTimeMode, float objectLifeTime) {
        this.objectLifeTime = objectLifeTime;
        this.startLifeTimeMode = startLifeTimeMode;
    }

    public void setTouchMode(boolean enable) {
        enableTouchMode = enable;
    }

    public void setOnClickListener(OnClickListener listener) {
        enableTouchMode = true;
        mClickHelper.setOnClickListener(listener);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (startLifeTimeMode) {
            if (livingTime > objectLifeTime) {
                if (getParent() != null)
                    getParent().removeActor(this);
            }
        }
        if (enableTouchMode) {
            mClickHelper.act(this);
        }
    }

    protected void clicked() {
    }

    public float getLivingTime() {
        return livingTime;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        Log.show("finalize " + getClass().getName());
    }

    @Override
    public void dispose() {
        background = null;
        currentFrame = null;
        animation = null;
    }

    public Rectangle rectangle() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    public class State {
        public Map<Integer, Object> mapKV = new HashMap<Integer, Object>();

        public State() {

        }

        protected void updateState(int state) {

        }
    }
}
