package com.mygdx.game.ViewActor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.SnapshotArray;
import com.mygdx.game.GameObjectActor.Card;

/**
 * Created by Administrator on 2015/9/11.
 */
public class RadioGroup extends LinearLayout {
    private final ClickHelper mClickHelper;
    private Actor selectedChild;

    public RadioGroup(int orientation) {
        super(orientation);
        mClickHelper = new ClickHelper() {
            @Override
            protected void clicked() {
                super.clicked();
                float y = Gdx.graphics.getHeight() - Gdx.input.getY();
                float x = Gdx.input.getX();
                findChild(x, y);
            }
        };
    }

    public void findChild(float x, float y) {
        SnapshotArray<Actor> array = getChildren();
        for (Actor actor : array) {
            if (actor instanceof RadioButton) {
                RadioButton button = ((RadioButton) actor);
                if (button.getClickHelper().act(actor)) {
                    button.checked(true);
                    selectedChild = actor;
                } else {
                    button.checked(false);
                }
            }
        }
    }

    public boolean isAllowCheckMode() {
        return true;
    }

    public Actor getSelected() {
        return selectedChild;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        mClickHelper.act(this);
    }
}
