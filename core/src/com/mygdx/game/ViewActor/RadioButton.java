package com.mygdx.game.ViewActor;

import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Administrator on 2015/9/11.
 */
public interface RadioButton {
    void checked(boolean checked);
    ClickHelper getClickHelper();
}
