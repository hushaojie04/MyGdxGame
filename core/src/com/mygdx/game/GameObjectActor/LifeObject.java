package com.mygdx.game.GameObjectActor;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.Utils.Log;
import com.mygdx.game.resource.Res;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2015/9/7.
 */
public class LifeObject extends GameObject {
    public final boolean isMove;
    private float speedX = 0, speedY = 0, distanceX = 0, distanceY = 0;
    protected Object skill;
    protected int attack;
    protected float defense;
    protected boolean isMoveNODistance = false;
    protected boolean isMoving = true;
    protected boolean isAttacking = false;
    protected boolean isAttacked = false;
    private float defenseTime = 0;
    private boolean isDead = false;
    private List<LifeObject> attackGroup = new ArrayList<LifeObject>();

    public LifeObject(Stage stage, Texture texture, float x, float y, float width, float height, boolean isMove) {
        super(stage, texture, x, y, width, height);
        this.isMove = isMove;
    }

    public LifeObject(Stage stage, Animation animation, float x, float y, boolean isMove) {
        super(stage, animation, x, y);
        this.isMove = isMove;
    }

    public void setSpeedX(float speed) {
        isMoveNODistance = false;
        this.speedX = speed;
    }

    /*
    s
     */
    public void moveDistance(float duration, float x, float y) {
        isMoveNODistance = true;
        distanceX = x;
        distanceY = y;
        if (distanceX != 0) {
            speedX = (distanceX - getX()) / duration * Gdx.graphics.getDeltaTime();
        }
        if (distanceY != 0) {
            speedY = (distanceY - getY()) / duration * Gdx.graphics.getDeltaTime();
        }
    }

    public void setSpeedY(float speed) {
        this.speedY = speed;
    }

    public void setSkill(Object skill) {
        this.skill = skill;
    }

    public Object getSkill(Object skill) {
        return skill;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getAttack() {
        return attack;
    }

    public float getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        checkAttack();
        if (attackGroup.size() > 0) {
            defenseTime += delta * attackGroup.size();
            Log.show("delta:" + delta);
            Log.show("delta:" + attackGroup.size());
            if (defenseTime > defense) {
                Log.show("defenseTime:" + defenseTime);
                isDead = true;
                attackGroup.clear();
                getParent().removeActor(this);
            }
        }
        if (isMove && isMoving) {
            if (isMoveNODistance) {
                if (distanceX == 0) {
                } else {

                    if (speedX < 0 && getX() > distanceX) {
                        setX(getX() + speedX);
                    } else if (speedX > 0 && getX() < distanceX) {
                        setX(getX() + speedX);
                    }
                }
                if (distanceY == 0) {
                } else {
                    if (speedY < 0 && getY() > distanceY) {
                        setY(getY() + speedY);
                    } else if (speedY > 0 && getY() < distanceY) {
                        setY(getY() + speedY);
                    }
                }
            } else {
                if (speedX != 0)
                    setX(getX() + speedX);
                if (speedX != 0)
                    setY(getY() + speedY);
            }
        }
    }

    private void checkAttack() {
        Iterator<LifeObject> iterator = attackGroup.iterator();
        while (iterator.hasNext()) {
            LifeObject object = iterator.next();
            if (!object.isDead()) {
                object.startMove();
                object.stopAttack();
            } else
                attackGroup.remove(object);
        }
    }

    public boolean isDead() {
        return isDead;
    }

    public void stopMove() {
        isMoving = false;
    }

    public void startMove() {
        isMoving = true;
    }

    public void stopAttack() {
        isAttacking = false;
    }

    public void startAttack() {
        isAttacking = true;
    }

    public void beingAttacked(LifeObject attackObject) {
        if (!attackGroup.contains(attackObject))
            attackGroup.add(attackObject);
    }
}
