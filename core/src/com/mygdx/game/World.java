package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.SnapshotArray;
import com.mygdx.game.GameObjectActor.GameObject;
import com.mygdx.game.Level.Level;
import com.mygdx.game.Utils.Log;
import com.mygdx.game.ViewActor.Card;
import com.mygdx.game.ViewActor.LinearLayout;
import com.mygdx.game.ViewActor.ScrollSod;
import com.mygdx.game.ViewActor.TextView;
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
    public List<Actor> mapSceneObjects = new ArrayList<Actor>();
    public LinearLayout mLinearlayout;
    public static float ratioH = 1f;
    public static float ratioW = 1f;
    public TextView sunBack;
    public int sunCount;
    Command mCommand;
    Stage stage;
    public float time;

    public void createGameObject(Level level, Stage stage) {
        this.stage = stage;
        mLinearlayout = new LinearLayout(LinearLayout.VERTICAL);
        mLinearlayout.setX(10);
        createBackground(stage);
        createZombieObjects(level, stage);

    }

    public void act(float delta) {
        time += delta;
        if (time > 3.0f) {
            time = 0;
            setSunBack();
        }

    }

    public void setSunBack() {
        sunCount += 50;
        sunBack.setText("" + sunCount);
        SnapshotArray<Actor> array = mLinearlayout.getChildren();
        for (Actor actor : array) {
            if (actor instanceof Card) {
                ((Card) actor).checkCost(sunCount);
            }
        }
    }

    public void start() {
        if (mCommand != null)
            mCommand.doCommand();
    }

    private void createBackground(final Stage stage) {
        GameObject background = new GameObject(stage, Res.getBackground1unsodded(), 0, 0, Gdx.graphics.getWidth() * 1.37f
                , Gdx.graphics.getHeight());
        mapSceneObjects.add(background);
        ratioH = (float) Gdx.graphics.getHeight() / (float) Res.getBackground1unsodded().getHeight();
        ratioW = (float) (Gdx.graphics.getWidth() * 1.37f) / (float) Res.getBackground1unsodded().getWidth();
        final ScrollSod scrollSod = new ScrollSod(Gdx.graphics.getWidth() * 0.24f, Gdx.graphics.getHeight() * 0.44f);
        scrollSod.setScrollEndListener(new ScrollSod.ScrollEndListener() {
            @Override
            public void end() {
                System.out.println("ScrollEndListener");
                createSunBack();
                createLawnMower();
                createCard();
            }
        });
        mCommand = new Command() {
            @Override
            public void doCommand() {
                mapSceneObjects.add(scrollSod);
                stage.addActor(scrollSod);
            }
        };
    }

    private void createLawnMower() {
        GameObject lawnMower = new GameObject(stage
                , Res.getLawnMower(), Gdx.graphics.getWidth() * 0.25f - Res.getLawnMower().getWidth() * World.ratioW
                , Gdx.graphics.getHeight() * 0.44f
                , Res.getLawnMower().getWidth() * World.ratioW
                , Res.getLawnMower().getHeight() * World.ratioH);
        gameSceneObjects.add(lawnMower);
    }

    private void createSunBack() {
        sunBack = new TextView(Res.getSunBack(), Res.getMyfont(), Color.BLACK);
        sunBack.setPadding(0.4f, 0.65f);
        sunBack.setX(Gdx.graphics.getWidth() * 0.3f);
        sunBack.setY(Gdx.graphics.getHeight() - sunBack.getHeight());
        stage.addActor(sunBack);
    }

    private void createCard() {
        mLinearlayout.add(new Card(Res.getPeashooterG(), Res.getPeashooter(), 150));
        mLinearlayout.add(new Card(Res.getSunFlowerG(), Res.getSunFlower(), 50));
        mLinearlayout.setPosition(mLinearlayout.getX(), Gdx.graphics.getHeight() - mLinearlayout.getHeight());
        stage.addActor(mLinearlayout);
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
                            texture.getWidth() * ratioH,
                            texture.getHeight() * ratioH);
                    zombieObjects.add(gameObject);
                }
            }

        }
    }

    interface Command {
        void doCommand();
    }
}
