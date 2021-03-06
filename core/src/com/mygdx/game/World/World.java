package com.mygdx.game.World;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;
import com.badlogic.gdx.utils.SnapshotArray;
import com.mygdx.game.GameObjectActor.GameObject;
import com.mygdx.game.GameObjectActor.LifeObject;
import com.mygdx.game.GameObjectActor.Plant;
import com.mygdx.game.GameObjectActor.BaseZombie;
import com.mygdx.game.Level.Level;
import com.mygdx.game.Model.Cell;
import com.mygdx.game.Plant.PlantFactory;
import com.mygdx.game.Utils.GifDecoder;
import com.mygdx.game.GameObjectActor.Card;
import com.mygdx.game.Utils.Log;
import com.mygdx.game.ViewActor.LinearLayout;
import com.mygdx.game.ViewActor.RadioGroup;
import com.mygdx.game.ViewActor.ScrollSod;
import com.mygdx.game.ViewActor.TextView;
import com.mygdx.game.impl.OnClickListener;
import com.mygdx.game.resource.Res;
import com.mygdx.game.zombie.Zombie;
import com.mygdx.game.zombie.ZombieFactory;

import java.util.ArrayList;
import java.util.Iterator;
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
    public RadioGroup mLinearlayout;
    public static float ratioH = 1f;
    public static float ratioW = 1f;
    public TextView sunBack;
    public int sunCount;
    public float time;
    private PlantFactory mPlantFactory;
    private ZombieFactory mZombieFactory;
    private Command mCommand;
    private Stage stage;
    private Stage highStage;
    private float worldTime;
    private Animation sunAnim;
    private Command createSumCommand;
    private Cell mCell;
    private Level mLevel;
    private int attackCount = -1;
    private float attackTime = 0;

    public World() {
        sunAnim = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("image/map/Sun.gif").read());
        mPlantFactory = new PlantFactory();
        mZombieFactory = new ZombieFactory();
        highStage = new Stage();
    }

    public void createGameObject(Level level, Stage stage) {
        this.stage = stage;
        mLinearlayout = new RadioGroup(LinearLayout.VERTICAL);
        mLinearlayout.setX(10);
        createBackground(stage);
        createZombieObjects(level, stage);
    }

    public void act(float delta) {
        if (isStart) {
            worldTime += delta;
            attackTime += delta;
            time += delta;
            if (time > 5f) {
                time = 0;
                if (createSumCommand != null) {
                    createSumCommand.doCommand();
                }
            }
            if (attackTime > 10f) {
                attackTime = 0;
                attackCount++;
                createAttackZombie();
            }
            if (mCell != null)
                loopCell();
        }
        highStage.act();
        highStage.draw();
    }

    public void createAttackZombie() {
        if (attackCount < mLevel.getZombiesContent().size()) {
            Level.ZombieInfo zombieInfo = mLevel.getZombiesContent().get(attackCount);
            if (zombieInfo.getType().equals("Zombie")) {
                int x = 0;
                for (int i = 0; i < zombieInfo.getCount(); i++) {
                    x += 100;
                    BaseZombie zombie = mZombieFactory.newZombie(BaseZombie.Kind.Zombie, highStage, Gdx.graphics.getWidth() + x, 500);
                    zombieObjects.add(zombie);
                }
            }

        } else {

        }
    }

    private void loopCell() {
        List<Cell.CellInfo> cellInfos = mCell.getCells();
        Iterator<Cell.CellInfo> iterator = cellInfos.iterator();
        while (iterator.hasNext()) {
            Cell.CellInfo info = iterator.next();
            if (info.isGrow) {
                Plant plant = (Plant) info.getTag();
                if (plant.isDead()) {
                    info.setTag(null);
                    info.isGrow = false;
                    continue;
                }
                List<GameObject> copyZombies = new ArrayList<GameObject>();
                copyZombies.addAll(zombieObjects);
                Iterator<GameObject> iterator1 = copyZombies.iterator();
                while (iterator1.hasNext()) {
                    BaseZombie zombie = (BaseZombie) iterator1.next();
                    if (zombie.rectangle().overlaps(plant.rectangle())) {
//                        Log.show("zombie:"+zombie.rectangle()+" plant:"+plant.rectangle());
                        zombie.stopMove();
                        zombie.startAttack();
                        plant.beingAttacked(zombie);
                    }
                }
            } else {
                if (Gdx.input.isTouched()) {
                    float x = Gdx.input.getX();
                    float y = Gdx.input.getY();
//                    Log.show("isTouched:" + x + " " + y);
                    if (info.rectangle.contains(x, y)) {
                        info.setTag(createPlant(info));
                    }
                }
            }

        }
    }


    public Plant createPlant(Cell.CellInfo cellInfo) {
        Card card = (Card) mLinearlayout.getSelected();
        if (card == null || !card.isGrow()) return null;
        cellInfo.isGrow = true;
        Card.Kind kind = card.getKind();
        Plant plant = null;
        plant = mPlantFactory.newPlant(kind, stage, cellInfo.rectangle.x, cellInfo.rectangle.y);

        setSunBack(-kind.cost);
        return plant;
    }

    private void createSun() {
        createSumCommand = new Command() {
            @Override
            public void doCommand() {
                float x = (float) (Gdx.graphics.getWidth() * 0.1f + Math.random() * (Gdx.graphics.getWidth() * 0.8f));
                LifeObject sun = new LifeObject(highStage, sunAnim, x, Gdx.graphics.getHeight(), false);
                sun.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(Object object) {
                        final GameObject actor = (GameObject) object;
//
                        MoveToAction action1 = Actions.moveTo(sunBack.getX(), sunBack.getY(), 0.3f);
                        ScaleToAction action2 = Actions.scaleTo(0.5f, 0.5f, 0.3f);
                        RunnableAction action3 = Actions.run(new Runnable() {
                            @Override
                            public void run() {
                                if (actor != null && actor.getParent() != null) {
                                    actor.setTouchMode(false);
                                    actor.getParent().removeActor(actor);
                                }
                                setSunBack(25);
                            }
                        });
                        ParallelAction Paction = Actions.parallel(action2, action1);
                        actor.addAction(Actions.sequence(Paction, action3));

                    }
                });
                sun.setLifeTime(true, 11f);
                float y = (float) (Gdx.graphics.getHeight() * 0.1f + Math.random() * (Gdx.graphics.getHeight() * 0.4f));
                MoveToAction action1 = Actions.moveTo(sun.getX(), y, 5f);
                sun.addAction(action1);
            }
        };
        createSumCommand.doCommand();
    }

    public void setSunBack(int countDelta) {
        sunCount += countDelta;
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

    private boolean isStart = false;

    public void start() {
        isStart = true;
        clearPreviewZombies();
        zombieObjects.clear();
        if (mCommand != null)
            mCommand.doCommand();
    }

    private void clearPreviewZombies() {
        Iterator<GameObject> iterator = zombieObjects.iterator();
        while (iterator.hasNext()) {
            GameObject object = iterator.next();
            object.getParent().removeActor(object);
        }
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
        Log.show("cell:", Gdx.graphics.getWidth() * 0.24f, Gdx.graphics.getHeight() * 0.44f,
                scrollSod.getWidth(), scrollSod.getHeight());
        mCell = new Cell(new Rectangle(Gdx.graphics.getWidth() * 0.24f, Gdx.graphics.getHeight() * 0.44f,
                scrollSod.getWidth(), scrollSod.getHeight()),
                1, 9);

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
        mLinearlayout.add(new Card(Res.getPeashooterG(), Res.getPeashooter(), Card.Kind.Peashooter));
        mLinearlayout.add(new Card(Res.getSunFlowerG(), Res.getSunFlower(), Card.Kind.SunFlower));
        mLinearlayout.setPosition(mLinearlayout.getX(), Gdx.graphics.getHeight() - mLinearlayout.getHeight());
        mapSceneObjects.add(mLinearlayout);
        stage.addActor(mLinearlayout);
    }

    private void createZombieObjects(Level level, Stage stage) {
        mLevel = level;
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
