package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.mygdx.game.Level.LevelManager;
import com.mygdx.game.World.MyGdxGame;
import com.mygdx.game.Utils.Log;
import com.mygdx.game.ViewHandler.ExitDialog;
import com.mygdx.game.ViewHandler.AreaButton;
import com.mygdx.game.impl.OnClickListener;
import com.mygdx.game.resource.MusicManager;
import com.mygdx.game.resource.Res;


/**
 * Created by Administrator on 2015/9/2.
 */
public class MenuScreen implements Screen {
    private final MyGdxGame myGdxGame;
    private Stage stage;
    private Image background;
    private Button adventureBtn, survivalBtn;
    private AreaButton mExitButton;

    public MenuScreen(MyGdxGame game) {
        myGdxGame = game;
    }

    private ExitDialog mAlertDialog;

    @Override
    public void show() {
        Log.show("munuscreen show");
        mAlertDialog = new ExitDialog();
        stage = new Stage();
        background = new Image(Res.getSurface());
        background.setFillParent(true);
        TextureRegion[][] spilt = TextureRegion.split(Res.getSelectorScreenStartAdventure(), 330, 146);
        adventureBtn = new Button(new SpriteDrawable(new Sprite(spilt[0][0])), new SpriteDrawable(new Sprite(spilt[1][0])));
        adventureBtn.setWidth(Gdx.graphics.getWidth() * 0.33f);
        adventureBtn.setHeight(Gdx.graphics.getHeight() * 0.33f);
        adventureBtn.setPosition(Gdx.graphics.getWidth() * 0.55f, Gdx.graphics.getHeight() * 0.55f);
        adventureBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                myGdxGame.startGameScreen();
            }
        });
        spilt = TextureRegion.split(Res.getSelectorScreenSurvival(), 313, 131);
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
        mExitButton = new AreaButton(1697, 97, 180, 180);
        mExitButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(Object object) {
                mAlertDialog.show(stage);
            }
        });


        stage.addActor(background);
        stage.addActor(adventureBtn);
        stage.addActor(survivalBtn);

        Gdx.input.setInputProcessor(stage);

        //        Skin skin = new Skin(Gdx.files.internal("json/skin.json"));
//        Label nameLabel = new Label("name", skin, "default");
//        nameLabel.setPosition(100, 100);
//        stage.addActor(nameLabel);
        new MusicManager();
        MusicManager.play();
        LevelManager.getManager().setLevel(1);
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
        mExitButton.act(v);
    }

    @Override
    public void resize(int i, int i1) {
        Log.show("munuscreen resize");
    }

    @Override
    public void pause() {
        Log.show("munuscreen pause");
        MusicManager.stop();
    }

    @Override
    public void resume() {
        Log.show("munuscreen resume");
        MusicManager.play();
    }

    @Override
    public void hide() {
        Log.show("munuscreen hide");
    }

    @Override
    public void dispose() {
        Log.show("munuscreen dispose");
    }
}
