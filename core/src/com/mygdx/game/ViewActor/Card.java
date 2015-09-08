package com.mygdx.game.ViewActor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.World;

/**
 * Created by Administrator on 2015/9/8.
 */
public class Card extends Actor {
    private int state;
    private final int cost;
    private boolean isEnoughLight = false;
    private Texture background, foreground;
    private Pixmap pixmap;
    private Texture texture;

    public Card(Texture background, Texture foreground, int cost) {
        this.cost = cost;
        this.background = background;
        this.foreground = foreground;
        createPixmap();
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
        batch.draw(foreground, 0, 0, background.getWidth() * World.ratioW, background.getHeight() * World.ratioH);
        batch.draw(texture, 0, 0, background.getWidth() * World.ratioW, background.getHeight() * World.ratioH);
    }

    public void checkCost(int wolrdLight) {
        isEnoughLight = wolrdLight >= cost;
    }
}
