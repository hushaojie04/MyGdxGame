package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateByAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleByAction;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.SnapshotArray;
import com.mygdx.game.GameObjectActor.GameObject;
import com.mygdx.game.GameObjectActor.LifeObject;
import com.mygdx.game.Level.Level;
import com.mygdx.game.Utils.GifDecoder;
import com.mygdx.game.Utils.Log;
import com.mygdx.game.ViewActor.Card;
import com.mygdx.game.ViewActor.LinearLayout;
import com.mygdx.game.ViewActor.ScrollSod;
import com.mygdx.game.ViewActor.TextView;
import com.mygdx.game.impl.OnClickListener;
import com.mygdx.game.resource.Res;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.Action;

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
    public float time;

    private Command mCommand;
    private Stage stage;
    private float worldTime;
    private Animation sunAnim;
    private Command createSumCommand;

    public World() {
        sunAnim = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("image/map/Sun.gif").read());
    }

    public void createGameObject(Level level, Stage stage) {
        this.stage = stage;
        mLinearlayout = new LinearLayout(LinearLayout.VERTICAL);
        mLinearlayout.setX(10);
        createBackground(stage);
        createZombieObjects(level, stage);
    }

    public void act(float delta) {
        worldTime += delta;
        time += delta;
        if (time > 5f) {
            time = 0;
            if (createSumCommand != null) {
                createSumCommand.doCommand();
            }
        }

    }

    private void createSun() {
        createSumCommand = new Command() {
            @Override
            public void doCommand() {
                float x = (float) (Gdx.graphics.getWidth() * 0.1f + Math.random() * (Gdx.graphics.getWidth() * 0.8f));
//                LifeObject sun = new LifeObject(stage, sunAnim, x, Gdx.graphics.getHeight(), true);
                LifeObject sun = new LifeObject(stage, sunAnim, 1000, 800, false);
                sun.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(Object object) {
                        final GameObject actor = (GameObject) object;
//                        actor.getParent().removeActor(actor);
                        actor.scaleBy(2,2);
                        actor.moveBy();
                        Log.show("onClick");
                        MoveByAction action1 = Actions.moveBy(150, 0, 3);
                        ScaleByAction action2 = Actions.scaleBy(1000f, 1000f, 3);
                        ParallelAction Paction = Actions.parallel(action2, action1);
//                        actor.addAction(Paction);
                        Actions.run(new Runnable() {
                            @Override
                            public void run() {
                                Log.show("Runnable");
                            }
                        });
//                        Actions.addListener(new EventListener() {
//                            @Override
//                            public boolean handle(Event event) {
//                                return false;
//                            }
//                        },true);
//                        setSunBack();
                    }
                });
//                sun.setLifeTime(true, 11f);
//                float y = (float) (Gdx.graphics.getHeight() * 0.1f + Math.random() * (Gdx.graphics.getHeight() * 0.4f));
//                sun.moveDistance(3, 0, y);
            }
        };
    }

    public void setSunBack() {
        sunCount += 50;
        if (sunBack != null)
            sunBack.setText("" + sunCount);
        SnapshotArray<Actor> array = mLinearlayout.getChildren();
        for (Actor actor : array) {
            if (actor instanceof Card) {
                ((Card) actor).checkCost(sunCount);
            }
        }

    }

    public float getWorldTime() {
        return worldTime;
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
                createSun();
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

        mapSceneObjects.add(sunBack);
        stage.addActor(sunBack);
    }

    private void createCard() {
        mLinearlayout.add(new Card(Res.getPeashooterG(), Res.getPeashooter(), 150));
        mLinearlayout.add(new Card(Res.getSunFlowerG(), Res.getSunFlower(), 50));
        mLinearlayout.setPosition(mLinearlayout.getX(), Gdx.graphics.getHeight() - mLinearlayout.getHeight());
        mapSceneObjects.add(mLinearlayout);
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
