package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.GameObjectActor.GameObject;
import com.mygdx.game.Level.Level;
import com.mygdx.game.resource.Res;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/9/7.
 */
public class World {
    public List<GameObject> plantObjects = new ArrayList<GameObject>();
    public List<GameObject> zombieObjects = new ArrayList<GameObject>();
    public List<GameObject> bulletObjects = new ArrayList<GameObject>();
    public List<GameObject> gameSceneObjects = new ArrayList<GameObject>();
    public List<GameObject> mapSceneObjects = new ArrayList<GameObject>();
    private float ratio;

    public void createGameObject(Level level, Stage stage) {
        createBackground(stage);
        createZombieObjects(level, stage);
    }

    private void createBackground(Stage stage) {
        GameObject background = new GameObject(stage, Res.getBackground1unsoddedTexture(), 0, 0, Gdx.graphics.getWidth() * 1.37f
                , Gdx.graphics.getHeight());
        mapSceneObjects.add(background);
        ratio = (float) Gdx.graphics.getHeight() / (float) Res.getBackground1unsoddedTexture().getHeight();
    }

    private void createZombieObjects(Level level, Stage stage) {
        List<Level.ZombieInfo> zombieContents = level.getZombiesContent();
        GameObject gameObject;
        for (Level.ZombieInfo info : zombieContents) {
            for (int i = 0; i < info.getCount(); i++) {
                if (info.getType().equals("Zombie")) {
                    Texture texture = Res.getZombie();
                    int x = (int) (Gdx.graphics.getWidth() + Math.random() * (Gdx.graphics.getWidth() * 0.1f));
                    int y = (int) (Gdx.graphics.getHeight() * 0.1f + Math.random() * (Gdx.graphics.getHeight() * 0.7f));
                    gameObject = new GameObject(stage, texture, x, y,
                            texture.getWidth() * ratio,
                            texture.getHeight() * ratio);
                    zombieObjects.add(gameObject);
                }
            }

        }
    }
}
