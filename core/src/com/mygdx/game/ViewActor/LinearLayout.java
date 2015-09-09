package com.mygdx.game.ViewActor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.Utils.Log;

import java.util.HashMap;


/**
 * Created by Administrator on 2015/9/6.
 */

public class LinearLayout extends Group {
    public final static int HORIZONTAL = 1;
    public final static int VERTICAL = 2;
    private final int orientation;
    private float tailX = 0, tailY = 0;


    public LinearLayout(int orientation) {
        this.orientation = orientation;
    }

    public void add(Actor actor) {
        addActor(actor);
        if (orientation == HORIZONTAL) {
            actor.setPosition(tailX, actor.getY());
            tailX += actor.getWidth();
            tailY = Math.max(actor.getY() + actor.getHeight(), tailY);
        } else {
            actor.setPosition(actor.getX(), tailY);
            tailX = Math.max(actor.getX() + actor.getWidth(), tailX);
            tailY += actor.getHeight();
        }
        this.setWidth(tailX);
        this.setHeight(tailY);
    }

    @Override
    public float getHeight() {
        return tailY;
    }
}
