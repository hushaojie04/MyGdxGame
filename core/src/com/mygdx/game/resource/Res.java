package com.mygdx.game.resource;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by Administrator on 2015/9/2.
 */
public class Res {
    private static AssetManager manager = new AssetManager();

    public static void load() {
        manager.load("image/Surface.png", Texture.class);
        manager.load("image/button/SelectorScreenStartAdventur.png", Texture.class);
        manager.load("image/button/SelectorScreenSurvival.png", Texture.class);
    }

    public static void unload() {
        manager.unload("image/Surface.png");
        manager.unload("image/button/SelectorScreenStartAdventur.png");
        manager.unload("image/button/SelectorScreenSurvival.png");

    }

    public static float update() {
        return manager.getProgress();
    }

    public static boolean isLoaded() {
        return manager.update();
    }

    public static Texture getSurfaceTexture() {
        return manager.get("image/Surface.png", Texture.class);
    }

    public static Texture getSelectorScreenStartAdventureTexture() {
        return manager.get("image/button/SelectorScreenStartAdventur.png", Texture.class);
    }

    public static Texture getSelectorScreenSurvivalTexture() {
        return manager.get("image/button/SelectorScreenSurvival.png", Texture.class);
    }
}
