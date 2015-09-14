package com.mygdx.game.screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.World.MyGdxGame;
import com.mygdx.game.resource.Res;

/**
 * Created by Administrator on 2015/9/2.
 */
public class LoadingScreen implements Screen {
    private Stage stage;
    private Image background;
    private float time;
    private final MyGdxGame myGdxGame;

    public LoadingScreen(MyGdxGame game) {
        myGdxGame = game;
        Res.load();
    }

    @Override
    public void show() {
        stage = new Stage();
        background = new Image(new Texture(Gdx.files
                .internal("image/Logo.jpg")));
        background.setFillParent(true);
    }

    @Override
    public void render(float v) {
        time += Gdx.graphics.getDeltaTime();
        if (time > 1.0) {
            if (Res.isLoaded()) {
                myGdxGame.openMenuScreen();
            }
//            System.out.println("percent " + Res.update());
        }
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.addActor(background);
        stage.act();
        stage.draw();
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
