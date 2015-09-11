package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.Level.Level;
import com.mygdx.game.Level.LevelManager;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Utils.AreaUtils;
import com.mygdx.game.Utils.Log;
import com.mygdx.game.ViewActor.ScrollSod;
import com.mygdx.game.impl.CameraAction;
import com.mygdx.game.World;


/**
 * Created by Administrator on 2015/9/7.
 */
public class GameScreen implements Screen {
    private Level currentLevel = null;
    private World world;
    private Camera camera;
    public float distance;
    private CameraAction cameraAction;

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
        Log.show("gamescreen show");
        stage = new Stage();
        world = new World();
        camera = stage.getCamera();
        cameraAction = new CameraAction(previewOnCameraAction);
        LevelManager.getManager().setLevel(1);
        currentLevel = LevelManager.getManager().getLevel();
        distance = Gdx.graphics.getWidth() * 0.37f;
        world.createGameObject(currentLevel, stage);
//        AreaUtils.init();
        AreaUtils.initFPS();
        Gdx.input.setInputProcessor(stage);
    }

    private boolean isPreview = false;

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        world.act(Gdx.graphics.getDeltaTime());
        stage.act();
        stage.draw();
//        AreaUtils.draw();
        if (!isPreview) {
            if (cameraAction.action(stage)) {
                isPreview = true;
                world.start();
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK)) {
            myGdxGame.openMenuScreen();
        }
        AreaUtils.drawFPS();
    }

    @Override
    public void resize(int i, int i1) {
        Log.show("gamescreen resize");
    }

    @Override
    public void pause() {
        Log.show("gamescreen pause");

    }

    @Override
    public void resume() {
        Log.show("gamescreen show");
    }

    @Override
    public void hide() {
        Log.show("gamescreen hide");

    }

    @Override
    public void dispose() {
        Log.show("gamescreen dispose");
    }

    private CameraAction.OnCameraAction previewOnCameraAction = new CameraAction.OnCameraAction() {
        private float time;
        private float distancex;
        private float speed = 20;

        @Override
        public boolean onAction(Stage stage) {
            time += Gdx.graphics.getDeltaTime();
            boolean endAction = false;
            if (time < 1) {
                if (distancex < distance) {
                    distancex += speed;
                    stage.getCamera().translate(speed, 0, 0);
                }
            } else if (time > 2 && time < 3) {
                if (distancex > 0) {
                    distancex -= speed;
                    stage.getCamera().translate(-speed, 0, 0);
                } else {
                    endAction = true;
                }
            }
            return endAction;
        }

    };
}
