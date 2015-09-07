package com.mygdx.game.ViewActor;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

/**
 * Created by Administrator on 2015/9/6.
 */

public class LinearLayout extends Group {
    public final static int HORIZONTAL = 1;
    public final static int VERTICAL = 2;
    private int orientation;
    private float tailX = 0, tailY = 0;

    public LinearLayout(int orientation) {
        this.orientation = orientation;
    }

    public void add(Actor actor) {
        if (orientation == HORIZONTAL) {
            addActor(actor);
            actor.setPosition(tailX, actor.getY());
            tailX += actor.getWidth();
            tailY = Math.max(actor.getY() + actor.getHeight(), tailY);
        } else {
            addActor(actor);
            actor.setPosition(actor.getX(), tailY);
            tailX = Math.max(actor.getX() + actor.getWidth(), tailX);
            tailY += actor.getHeight();
        }
        this.setWidth(tailX);
        this.setHeight(tailY);
    }
}
