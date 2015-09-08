package com.mygdx.game.impl;


import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by Administrator on 2015/9/7.
 */
public class CameraAction {
    private final OnCameraAction onCameraAction;

    public CameraAction(OnCameraAction onCameraAction) {
        this.onCameraAction = onCameraAction;
    }

    public boolean action(Stage stage) {
        return onCameraAction.onAction(stage);
    }

    public interface OnCameraAction {
        boolean onAction(Stage stage);
    }
}
