package com.mygdx.game.GameObjectActor;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by Administrator on 2015/9/7.
 */
public class LifeObject extends GameObject {
    public final boolean isMove;
    private float speed;
    private Object skill;
    private int attack;
    private int defense;

    public LifeObject(Stage stage, Texture texture, float x, float y, float width, float height, boolean isMove) {
        super(stage, texture, x, y, width, height);
        this.isMove = isMove;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
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
            setX(getX() + speed);
        }
    }
}
