package com.mygdx.game.GameObjectActor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.Utils.Log;
import com.mygdx.game.ViewActor.RadioButton;
import com.mygdx.game.World.World;
import com.mygdx.game.resource.Res;

/**
 * Created by Administrator on 2015/9/8.
 */
public class Card extends GameObject implements RadioButton {
    private boolean isEnoughLight = false;
    private boolean isCD = false;
    private Texture background, foreground;
    private Pixmap pixmap;
    private Texture texture, pressed;
    private BitmapFont bitmapFont;
    private boolean isPressed = false;
    private final Kind kind;

    public Card(Texture background, Texture foreground, Kind kind) {
        super();
        this.kind = kind;
        setTouchMode(true);
        this.background = background;
        this.foreground = foreground;
        createPixmap();
        createPressed();
        setWidth(background.getWidth() * World.ratioW);
        setHeight(background.getHeight() * World.ratioH);
        bitmapFont = Res.getMyfont();
        bitmapFont.setColor(Color.BLACK);
    }

    public Kind getKind() {
        return kind;
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
        if (isEnoughLight && isPressed && pressed != null)
            batch.draw(pressed, getX(), getY(), pressed.getWidth() * World.ratioW, pressed.getHeight() * World.ratioH);
        bitmapFont.draw(batch, "" + kind.cost, getX() + background.getWidth() * World.ratioW * 0.6f, getY() + background.getHeight() * World.ratioH * 0.3f);
    }

    private void createPressed() {
        Pixmap pixmap = new Pixmap(background.getWidth(), background.getHeight(), Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.YELLOW.r, Color.YELLOW.g, Color.YELLOW.b,
                Color.YELLOW.a);
        pixmap.fillRectangle(1, 1, background.getWidth() / 3, 5);// 画线
        pixmap.fillRectangle(background.getWidth() / 3 * 2, 1, background.getWidth(), 5);// 画线
        pixmap.fillRectangle(1, background.getHeight() - 5, background.getWidth() / 3, background.getHeight());// 画线
        pixmap.fillRectangle(background.getWidth() / 3 * 2, background.getHeight() - 5, background.getWidth(), background.getHeight());// 画线

        pixmap.fillRectangle(1, 1, 5, background.getHeight() / 3);// 画线
        pixmap.fillRectangle(1, background.getHeight() / 3 * 2, 5, background.getHeight());
        pixmap.fillRectangle(background.getWidth() - 5, 1, background.getWidth(), background.getHeight() / 3);// 画线
        pixmap.fillRectangle(background.getWidth() - 5, background.getHeight() / 3 * 2, background.getWidth(), background.getHeight());
        pressed = new Texture(pixmap);
        pixmap.dispose();
    }

    public void checkCost(int wolrdLight) {
        isEnoughLight = wolrdLight >= kind.cost;
    }

    @Override
    public void checked(boolean checked) {
        if (isEnoughLight && !isCD)
            isPressed = checked;
    }



    public boolean isGrow() {
        Log.show("isEnoughLight:" + isEnoughLight);
        return isEnoughLight && !isCD;
    }

    public enum Kind {
        SunFlower(50), Peashooter(150);
        public int cost;

        Kind(int cost) {
            this.cost = cost;
        }
    }
}
