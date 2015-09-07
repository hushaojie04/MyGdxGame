package com.mygdx.game.ViewHandler;

import javafx.stage.Stage;

/**
 * Created by Administrator on 2015/9/7.
 */
public class CameraAction {
    private final OnCameraAction onCameraAction;

    public CameraAction(OnCameraAction onCameraAction) {
        this.onCameraAction = onCameraAction;
    }

    public void action(Stage stage) {
        onCameraAction.onAction(stage);
    }

    public interface OnCameraAction {
        void onAction(Stage stage);
    }
}
