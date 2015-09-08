package com.mygdx.game.ViewActor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.Utils.Log;

import java.util.HashMap;

/**
 * Created by Administrator on 2015/9/6.
 */

public class LinearLayout extends Group {
    public final static int HORIZONTAL = 1;
    public final static int VERTICAL = 2;
    private final int alian;
    private final int orientation;
    private float tailX = 0, tailY = 0;

    public LinearLayout(int orientation) {
        this(orientation == HORIZONTAL ? Align.left : Align.bottom, orientation);

    }


    public LinearLayout(int alian, int orientation) {
        this.orientation = orientation;
        this.alian = alian;
        if (orientation == HORIZONTAL)
            setWidth(Gdx.graphics.getWidth());
        else
            setHeight(Gdx.graphics.getHeight());
    }

    public void add(Actor actor) {
        if (orientation == HORIZONTAL) {
            if (alian == Align.right) {
                addActor(actor);
                actor.setPosition(tailX, actor.getY());
                tailX += actor.getWidth();
                tailY = Math.max(actor.getY() + actor.getHeight(), tailY);
            } else {
                addActor(actor);
                actor.setPosition(tailX, actor.getY());
                tailX += actor.getWidth();
                tailY = Math.max(actor.getY() + actor.getHeight(), tailY);
            }
        } else {
            Log.show("###V##");
            if (alian == Align.top) {
                addActor(actor);
                actor.setPosition(actor.getX(), Gdx.graphics.getHeight() - tailY);
                Log.show("###top##");
                tailY += actor.getHeight();
                tailX = Math.max(actor.getX() + actor.getWidth(), tailX);
            } else {
                addActor(actor);
                actor.setPosition(actor.getX(), tailY);
                tailX = Math.max(actor.getX() + actor.getWidth(), tailX);
                tailY += actor.getHeight();
            }

        }
        this.setWidth(tailX);
        this.setHeight(tailY);
    }
}
