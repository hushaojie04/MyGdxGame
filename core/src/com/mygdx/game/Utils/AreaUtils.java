package com.mygdx.game.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.ViewActor.ProgressBar;


/**
 * Created by Administrator on 2015/9/2.
 */
public class AreaUtils {
    private static Texture texture;
    private static Image image;
    private static Stage stage;
    private static ProgressBar mProgressBar;
    private static BitmapFont mBitmapFont;
    private static SpriteBatch spriteBatch;

    public static void init() {
        init(0, 0, 180, 180);
    }


    private static float X, Y, Width, Height;

    public static void init(float x, float y, float width, float height) {
        X = x;
        Y = y;
        Width = width;
        Height = height;
        if (spriteBatch == null)
            spriteBatch = new SpriteBatch();
        if (mBitmapFont == null)
            mBitmapFont = new BitmapFont(Gdx.files.internal("font/myfont.fnt"), Gdx.files.internal("font/myfont.png"), false);
        if (pixmap == null) {
            pixmap = new Pixmap(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), Pixmap.Format.RGBA8888);
            pixmap.setColor(Color.BLUE.r, Color.BLUE.g, Color.BLUE.b, Color.BLUE.a / 2);
        }
        stage = new Stage();
        texture = new Texture(Gdx.files.internal("badlogic.jpg"));
        image = new Image(texture);
        image.setWidth(width);
        image.setHeight(height);
        image.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                return false;
            }
        });
        mProgressBar = new ProgressBar(0, 0, new Texture(Gdx.files.internal("black.png")), new Texture(Gdx.files.internal("green.png")));
        stage.addActor(image);
        stage.addActor(mProgressBar);
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(stage);
        Gdx.input.setInputProcessor(inputMultiplexer);

    }

    public static void initPixmap(float x, float y, float width, float height) {
        X = x;
        Y = y;
        Width = width;
        Height = height;
        if (spriteBatch == null)
            spriteBatch = new SpriteBatch();
        if (mBitmapFont == null)
            mBitmapFont = new BitmapFont(Gdx.files.internal("font/myfont.fnt"), Gdx.files.internal("font/myfont.png"), false);
        if (pixmap == null)
            pixmap = new Pixmap((int) width, (int) height, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLUE.r, Color.BLUE.g, Color.BLUE.b, Color.BLUE.a / 2);
        texture = new Texture(pixmap);
    }

    static Pixmap pixmap;
    static String show = " ";

    public static void draw() {
        if (Gdx.input.isTouched()) {
            int x = Gdx.input.getX();
            int y = Gdx.graphics.getHeight() - Gdx.input.getY();
            if (!mProgressBar.contains(x, y)) {
                System.out.println(x + " " + y + " " + image.getWidth() + " " + image.getHeight());
                image.setPosition(x - image.getWidth() / 2f, y - image.getHeight() / 2f);
                float r = (float) image.getX() / (float) Gdx.graphics.getWidth();
                show = image.getX() + " " + image.getY() + " " + image.getWidth() + " " + image.getHeight() + " " + r;
            }

        }

        stage.act();
        stage.draw();
        spriteBatch.begin();
        mBitmapFont.draw(spriteBatch, show, 150, 90);
        spriteBatch.end();
    }

    private static float elapsedRealtimeMs;
    private static int frameCount = 0;

    private static void logFrameRate() {
        elapsedRealtimeMs += Gdx.graphics.getDeltaTime();
        if (elapsedRealtimeMs >= 1.0) {
//            Log.show((float)frameCount / elapsedRealtimeMs + "fps");
            show = "fps:" + frameCount / elapsedRealtimeMs;
            frameCount = 0;
            elapsedRealtimeMs = 0;
        }
        frameCount++;
    }

    public static void initFPS() {
        spriteBatch = new SpriteBatch();
        mBitmapFont = new BitmapFont(Gdx.files.internal("font/myfont.fnt"), Gdx.files.internal("font/myfont.png"), false);
        mBitmapFont.setColor(Color.RED);
    }

    public static void drawFPS() {
        logFrameRate();
        spriteBatch.begin();
        mBitmapFont.draw(spriteBatch, show, 150, 90);
        spriteBatch.end();
    }

    public static void drawRect() {
        spriteBatch.begin();
        spriteBatch.draw(texture, X, Y);
        spriteBatch.end();
    }
}
