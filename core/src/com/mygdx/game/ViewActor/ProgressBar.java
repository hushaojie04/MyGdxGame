package com.mygdx.game.ViewActor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by Administrator on 2015/9/2.
 */
public class ProgressBar extends Actor implements Disposable {
    public final int x, y;
    private final Texture background;
    private final Texture foreground;
    private float progress = 0;
    private Rectangle rectangle;

    public ProgressBar(int x, int y, Texture background, Texture foreground) {
        this.x = x;
        this.y = y;
        this.background = background;
        this.foreground = foreground;
        this.setX(x);
        this.setY(y);
        rectangle = new Rectangle(x, y, background.getWidth(), background.getHeight());
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }

    public float getProgress() {
        return progress;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (Gdx.input.isTouched()) {
            int x = Gdx.input.getX();
            int y = Gdx.graphics.getHeight() - Gdx.input.getY();
//            System.out.println("contains = " + rectangle.contains(x, y));
            if (rectangle.contains(x, y)) {
                float percent = (float) (x - rectangle.getX()) / (float) rectangle.getWidth();
                progress = percent;
            }
        }
        batch.draw(background, x, y, background.getWidth(), background.getHeight());
        batch.draw(foreground, x, y, foreground.getWidth() * progress, foreground.getHeight());
    }

    public boolean contains(int x, int y) {
        return rectangle.contains(x, y);
    }

    @Override
    public void dispose() {

    }

}
