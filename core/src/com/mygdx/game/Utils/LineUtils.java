package com.mygdx.game.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by Administrator on 2015/10/9.
 */
public class LineUtils {
    private static BitmapFont mBitmapFont;
    private static SpriteBatch spriteBatch;
    private static Stage stage;
    Pixmap pixmap;
    private Rectangle rectangleLeft, rectangleRight;
    Image imageLeft, imageRight;

    public LineUtils() {
        Texture texture = new Texture(40, Gdx.graphics.getHeight(), Pixmap.Format.RGBA8888);
        if (spriteBatch == null)
            spriteBatch = new SpriteBatch();
        if (mBitmapFont == null)
            mBitmapFont = new BitmapFont(Gdx.files.internal("font/myfont.fnt"), Gdx.files.internal("font/myfont.png"), false);
        if (pixmap == null)
            pixmap = new Pixmap(40, Gdx.graphics.getHeight(), Pixmap.Format.RGBA8888);
        rectangleLeft = new Rectangle(0, 0, 40, Gdx.graphics.getHeight());
        rectangleRight = new Rectangle(1000, 0, 40, Gdx.graphics.getHeight());

        pixmap.setColor(Color.BLUE.r, Color.BLUE.g, Color.BLUE.b, Color.BLUE.a / 2);
        pixmap.fillRectangle((int) 0, (int) 0, (int) rectangleLeft.width, (int) rectangleLeft.height);
        texture.draw(pixmap, 0, 0);
        TextureRegion region = new TextureRegion(texture, 40, Gdx.graphics.getHeight());
        imageLeft = new Image(region);
        imageRight = new Image(region);
        stage = new Stage();
        stage.addActor(imageLeft);
        stage.addActor(imageRight);
        imageRight.setX(rectangleRight.x);
        Gdx.input.setInputProcessor(stage);
    }

    String show = "111";

    public void draw() {

//        pixmap.setColor(Color.BLUE);
//        pixmap.fillRectangle((int) rectangleLeft.x, (int) rectangleLeft.y, (int) rectangleLeft.width, (int) rectangleLeft.height);
//        pixmap.fillRectangle((int) rectangleRight.x, (int) rectangleRight.y, (int) rectangleRight.width, (int) rectangleRight.height);
//        texture.draw(pixmap, 0, 0);

        //
        stage.draw();

        spriteBatch.begin();
        mBitmapFont.draw(spriteBatch, " " + rectangleLeft.x, rectangleLeft.x - 5, Gdx.graphics.getHeight() / 2);
        mBitmapFont.draw(spriteBatch, " " + rectangleRight.x, rectangleRight.x + 5, Gdx.graphics.getHeight() / 2);
        mBitmapFont.draw(spriteBatch, "space: " + (rectangleRight.x - rectangleLeft.x), rectangleRight.x + 5, Gdx.graphics.getHeight() * 0.8f);
        spriteBatch.end();
    }

    public void act() {
        stage.act();
        if (Gdx.input.isTouched()) {
            float x = Gdx.input.getX();
            float y = Gdx.input.getY();
            if (rectangleLeft.contains(x, y) && rectangleLeft.y < rectangleRight.x) {
                rectangleLeft.x = x - rectangleLeft.getWidth() / 2;
                imageLeft.setX(rectangleLeft.x);
            }
            if (rectangleRight.contains(x, y) && rectangleRight.x > rectangleLeft.y) {
                rectangleRight.x = x - rectangleRight.getWidth() / 2;
                imageRight.setX(rectangleRight.x);
            }
        }
    }


}
