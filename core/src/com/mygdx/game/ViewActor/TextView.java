package com.mygdx.game.ViewActor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.World;

/**
 * Created by Administrator on 2015/9/9.
 */
public class TextView extends Actor {
    private final Texture texture;
    private final BitmapFont bitmapFont;
    private final Color color;
    private String text = "";
    private float leftPadding, bottomPadding;

    public TextView(Texture texture, BitmapFont bitmapFont, Color color) {
        this.texture = texture;
        this.bitmapFont = bitmapFont;
        this.color = color;
        bitmapFont.setColor(color);
        setWidth(texture.getWidth() * World.ratioW);
        setHeight(texture.getHeight() * World.ratioH);
    }
    public void Scale(float scale)
    {

    }
    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setPadding(float leftRatio, float bottomRatio) {
        leftPadding = leftRatio;
        bottomPadding = bottomRatio;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
        bitmapFont.draw(batch, text,
                getX() + leftPadding * getWidth(),
                getY() + bottomPadding * getHeight());
    }
}
