package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Utils.AreaUtils;
import com.mygdx.game.resource.Res;


/**
 * Created by Administrator on 2015/9/2.
 */
public class MenuScreen implements Screen {
    private final MyGdxGame myGdxGame;
    private Stage stage;
    private Image background;
    private Button adventureBtn, survivalBtn;

    public MenuScreen(MyGdxGame game) {
        myGdxGame = game;
    }

    @Override
    public void show() {

        stage = new Stage();
        background = new Image(Res.getSurfaceTexture());
        background.setFillParent(true);
        TextureRegion[][] spilt = TextureRegion.split(Res.getSelectorScreenStartAdventureTexture(), 330, 146);
        adventureBtn = new Button(new SpriteDrawable(new Sprite(spilt[0][0])), new SpriteDrawable(new Sprite(spilt[1][0])));
        adventureBtn.setWidth(Gdx.graphics.getWidth() * 0.33f);
        adventureBtn.setHeight(Gdx.graphics.getHeight() * 0.33f);
        adventureBtn.setPosition(Gdx.graphics.getWidth() * 0.55f, Gdx.graphics.getHeight() * 0.55f);

        spilt = TextureRegion.split(Res.getSelectorScreenSurvivalTexture(), 313, 131);
        survivalBtn = new Button(new SpriteDrawable(new Sprite(spilt[0][0])), new SpriteDrawable(new Sprite(spilt[1][0])));
        survivalBtn.setWidth(Gdx.graphics.getWidth() * 0.3f);
        survivalBtn.setHeight(Gdx.graphics.getHeight() * 0.3f);
        survivalBtn.setPosition(Gdx.graphics.getWidth() * 0.55f, Gdx.graphics.getHeight() * 0.30f);
        survivalBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);

            }
        });
        stage.addActor(background);
        stage.addActor(adventureBtn);
        stage.addActor(survivalBtn);
        AreaUtils.init();
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
        AreaUtils.draw();
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
