package com.mygdx.game.GameObjectActor;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by Administrator on 2015/9/7.
 */
public class GameObject extends Actor {
    public final Texture background;

    public GameObject(Stage stage, Texture texture, float x, float y, float width, float height) {
        super();
        System.out.println(x + " " + y + " " + width + " " + height);
        background = texture;
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
        batch.draw(background, getX(), getY(), getWidth(), getHeight());
    }
}
