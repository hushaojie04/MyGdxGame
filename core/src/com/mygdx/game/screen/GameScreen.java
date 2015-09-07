package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.GameObjectActor.GameObject;
import com.mygdx.game.Level.Level;
import com.mygdx.game.Level.LevelManager;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Utils.AreaUtils;
import com.mygdx.game.World;
import com.mygdx.game.resource.Res;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2015/9/7.
 */
public class GameScreen implements Screen {
    private Level currentLevel = null;
    private World world;
    private Camera camera;
    public float distance;

    interface onLevelChangeListener {
        void onLevelChange();
    }

    private final MyGdxGame myGdxGame;
    Stage stage;

    public GameScreen(MyGdxGame game) {
        myGdxGame = game;
    }

    @Override
    public void show() {
        stage = new Stage();
        world = new World();
        camera = stage.getCamera();

        LevelManager.getManager().setLevel(1);
        currentLevel = LevelManager.getManager().getLevel();
        distance = Gdx.graphics.getWidth() * 0.37f;

        world.createGameObject(currentLevel, stage);
//        AreaUtils.init();

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
//        AreaUtils.draw();
        previewMap();

    }

    private boolean isPreview = false;
    float time;
    float distancex;
    float speed = 20;

    private void previewMap() {
        time += Gdx.graphics.getDeltaTime();
        if (!isPreview) {
            if (time < 1) {
                if (distancex < distance) {
                    distancex += speed;
                    camera.translate(speed, 0, 0);
                }
            }
            else if (time > 2 && time < 3) {
                if (distancex > 0) {
                    distancex -= speed;
                    camera.translate(-speed, 0, 0);
                }
            }
        }
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
