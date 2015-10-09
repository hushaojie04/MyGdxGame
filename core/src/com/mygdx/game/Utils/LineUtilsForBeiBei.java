package com.mygdx.game.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by Administrator on 2015/10/9.
 */
public class LineUtilsForBeiBei {
    private static BitmapFont mBitmapFont;
    private static SpriteBatch spriteBatch;
    private static Stage stage;
    Pixmap pixmap;
    Texture texture;
    private Rectangle rectangleLeft, rectangleRight;

    public LineUtilsForBeiBei() {
        texture = new Texture(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), Pixmap.Format.RGBA8888);
        if (spriteBatch == null)
            spriteBatch = new SpriteBatch();
        if (mBitmapFont == null)
            mBitmapFont = new BitmapFont(Gdx.files.internal("font/myfont.fnt"), Gdx.files.internal("font/myfont.png"), false);
        if (pixmap == null)
            pixmap = new Pixmap(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLUE.r, Color.BLUE.g, Color.BLUE.b, Color.BLUE.a);
        rectangleLeft = new Rectangle(0, 0, 40, Gdx.graphics.getHeight());
        rectangleRight = new Rectangle(0, 0, Gdx.graphics.getWidth(), 40);
        pixmap.setColor(Color.BLUE);
        pixmap.fillRectangle((int) rectangleLeft.x, (int) rectangleLeft.y, (int) rectangleLeft.width, (int) rectangleLeft.height);
        pixmap.setColor(Color.RED);
        pixmap.fillRectangle((int) rectangleRight.x, (int) rectangleRight.y, (int) rectangleRight.width, (int) rectangleRight.height);
        texture.draw(pixmap, 0, 0);
        TextureRegion region = new TextureRegion(texture, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Image image = new Image(region);
        stage = new Stage();
        stage.addActor(image);
        Gdx.input.setInputProcessor(stage);
    }

    String show = "111";

    public void draw() {

//        pixmap.setColor(Color.BLUE);
//        pixmap.fillRectangle((int) rectangleLeft.x, (int) rectangleLeft.y, (int) rectangleLeft.width, (int) rectangleLeft.height);
//        pixmap.fillRectangle((int) rectangleRight.x, (int) rectangleRight.y, (int) rectangleRight.width, (int) rectangleRight.height);
//        texture.draw(pixmap, 0, 0);

        spriteBatch.begin();
        mBitmapFont.draw(spriteBatch, show, 150, 90);
        spriteBatch.end();
        //
        stage.draw();
    }

    public void act() {
        stage.act();
        if (Gdx.input.isTouched()) {
            float x = Gdx.input.getX();
            float y = Gdx.input.getY();
            rectangleLeft.x = x - rectangleLeft.getWidth() / 2;
            rectangleRight.y = y - rectangleRight.getHeight() / 2;
            pixmap.setColor(Color.BLACK);
            pixmap.fill();
            pixmap.setColor(Color.RED);
            pixmap.fillRectangle((int) rectangleLeft.x, (int) rectangleLeft.y, (int) rectangleLeft.width, (int) rectangleLeft.height);
            pixmap.setColor(Color.BLUE);
            pixmap.fillRectangle((int) rectangleRight.x, (int) rectangleRight.y, (int) rectangleRight.width, (int) rectangleRight.height);
            texture.draw(pixmap, 0, 0);
        }
    }


}
