package com.mygdx.game.GameObjectActor;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.Utils.Log;

/**
 * Created by Administrator on 2015/9/7.
 */
public class LifeObject extends GameObject {
    public final boolean isMove;
    private float speedX = -1, speedY = -1, distanceX = 0, distanceY = 0;
    private Object skill;
    private int attack;
    private int defense;

    public LifeObject(Stage stage, Texture texture, float x, float y, float width, float height, boolean isMove) {
        super(stage, texture, x, y, width, height);
        this.isMove = isMove;
    }

    public LifeObject(Stage stage, Animation animation, float x, float y, boolean isMove) {
        super(stage, animation, x, y);
        this.isMove = isMove;
    }
    public void setSpeedX(float speed) {
        this.speedX = speed;
    }

    /*
    s
     */
    public void moveDistance(float duration, float x, float y) {
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

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (isMove) {
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
        }
    }
}
