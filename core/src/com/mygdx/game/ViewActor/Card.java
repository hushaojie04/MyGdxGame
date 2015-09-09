package com.mygdx.game.ViewActor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.World;
import com.mygdx.game.resource.Res;

/**
 * Created by Administrator on 2015/9/8.
 */
public class Card extends Actor {
    private final int cost;
    private boolean isEnoughLight = true;
    private boolean isCD = false;
    private Texture background, foreground;
    private Pixmap pixmap;
    private Texture texture, pressed;
    private BitmapFont bitmapFont;

    public Card(Texture background, Texture foreground, int cost) {
        this.cost = cost;
        this.background = background;
        this.foreground = foreground;
        createPixmap();
        createPressed();
        setWidth(background.getWidth() * World.ratioW);
        setHeight(background.getHeight() * World.ratioH);
        bitmapFont = Res.getMyfont();
        bitmapFont.setColor(Color.BLACK);
    }

    public void createPixmap() {
        pixmap = new Pixmap(background.getWidth(), background.getHeight(), Pixmap.Format.RGBA8888);
        // 绘制一个蓝方块到Ball图像之上
        pixmap.setColor(Color.GRAY.r, Color.GRAY.g, Color.GRAY.b,
                0.5f);
        // 以指定Pixmap构建Texture
        pixmap.fillRectangle(0, 0, background.getWidth(), background.getHeight());
        texture = new Texture(pixmap);
        // 注入Texture后的pixmap已经没用，可以注销
        pixmap.dispose();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (isEnoughLight) {
            batch.draw(foreground, getX(), getY(), background.getWidth() * World.ratioW, background.getHeight() * World.ratioH);
        } else {
            batch.draw(background, getX(), getY(), background.getWidth() * World.ratioW, background.getHeight() * World.ratioH);
        }
        if (Gdx.input.isTouched()) {
//            batch.draw(pressed, 0, 0, pressed.getWidth() * World.ratioW, pressed.getHeight() * World.ratioH);
        } else {
        }
        bitmapFont.draw(batch, "" + cost, getX() + background.getWidth() * World.ratioW * 0.6f, getY() + background.getHeight() * World.ratioH * 0.3f);
    }

    private void createPressed() {
        Pixmap pixmap = new Pixmap(background.getWidth(), background.getHeight(), Pixmap.Format.RGBA8888);
        // 绘制一个蓝方块到Ball图像之上
        pixmap.setColor(Color.YELLOW.r, Color.YELLOW.g, Color.YELLOW.b,
                Color.YELLOW.a);
        // 以指定Pixmap构建Texture
        pixmap.drawRectangle(-1, -1, background.getWidth() + 1, background.getHeight() + 1);
        pressed = new Texture(pixmap);
        // 注入Texture后的pixmap已经没用，可以注销
        pixmap.dispose();
    }

    public void checkCost(int wolrdLight) {
        isEnoughLight = wolrdLight >= cost;
    }
}
