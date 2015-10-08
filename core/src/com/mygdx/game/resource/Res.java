package com.mygdx.game.resource;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.Utils.GifDecoder;

import java.io.InputStream;

/**
 * Created by Administrator on 2015/9/2.
 */
public class Res {
    private static AssetManager manager = new AssetManager();

    public static void load() {
        /* ******************************Texture********************************* */
        manager.load("image/Surface.png", Texture.class);
        manager.load("image/button/SelectorScreenStartAdventur.png", Texture.class);
        manager.load("image/button/SelectorScreenSurvival.png", Texture.class);
        manager.load("image/background1unsodded.jpg", Texture.class);
        manager.load("image/Zombie/Zombie.gif", Texture.class);
        manager.load("image/map/SodRoll.png", Texture.class);
        manager.load("image/map/SodRollCap.png", Texture.class);
        manager.load("image/map/sod1row.png", Texture.class);
        manager.load("image/map/GrowSoil.png", Texture.class);
        manager.load("image/map/LawnMower.gif", Texture.class);
        manager.load("image/card/Peashooter.bmp", Texture.class);
        manager.load("image/card/Peashooter.png", Texture.class);
        manager.load("image/card/PeashooterG.png", Texture.class);
        manager.load("image/card/SunFlower.png", Texture.class);
        manager.load("image/card/SunFlowerG.png", Texture.class);
        manager.load("image/card/SunBack.png", Texture.class);
        manager.load("image/Zombie/FlagMeterEmpty.png", Texture.class);
        manager.load("image/Zombie/FlagMeterFull.png", Texture.class);
        manager.load("image/Zombie/FlagMeterParts1.png", Texture.class);
        manager.load("image/map/plantshadow32.png", Texture.class);
        manager.load("image/map/plantshadow8.png", Texture.class);
      /* ******************************BitmatFont********************************* */
        manager.load("font/myfont.fnt", BitmapFont.class);

    }

    public static void unload() {
         /* ******************************Texture********************************* */
        manager.unload("image/Surface.png");
        manager.unload("image/button/SelectorScreenStartAdventur.png");
        manager.unload("image/button/SelectorScreenSurvival.png");
        manager.unload("image/background1unsodded.jpg");
        manager.unload("image/Zombie/Zombie.gif");
        manager.unload("image/map/SodRoll.png");
        manager.unload("image/map/SodRollCap.png");
        manager.unload("image/map/sod1row.png");
        manager.unload("image/map/GrowSoil.png");
        manager.unload("image/map/LawnMower.gif");
        manager.unload("image/card/Peashooter.bmp");
        manager.unload("image/card/Peashooter.png");
        manager.unload("image/card/PeashooterG.png");
        manager.unload("image/card/SunFlower.png");
        manager.unload("image/card/SunFlowerG.png");
        manager.unload("image/card/SunBack.png");
        manager.unload("image/Zombie/FlagMeterEmpty.png");
        manager.unload("image/Zombie/FlagMeterFull.png");
        manager.unload("image/Zombie/FlagMeterParts1.png");
        manager.unload("image/map/plantshadow32.png");
        manager.unload("image/map/plantshadow8.gif");

        /* ******************************BitmatFont********************************* */
        manager.unload("font/myfont.fnt");

    }

    public static float update() {
        return manager.getProgress();
    }

    public static boolean isLoaded() {
        return manager.update();
    }

    /* ******************************Texture********************************* */
    public static Texture getSurface() {
        return manager.get("image/Surface.png", Texture.class);
    }

    public static Texture getSelectorScreenStartAdventure() {
        return manager.get("image/button/SelectorScreenStartAdventur.png", Texture.class);
    }

    public static Texture getSelectorScreenSurvival() {
        return manager.get("image/button/SelectorScreenSurvival.png", Texture.class);
    }

    public static Texture getBackground1unsodded() {
        return manager.get("image/background1unsodded.jpg", Texture.class);
    }

    public static Texture getZombie() {
        return manager.get("image/Zombie/Zombie.gif", Texture.class);
    }

    public static Texture getSodRoll() {
        return manager.get("image/map/SodRoll.png", Texture.class);
    }

    public static Texture getSodRollCap() {
        return manager.get("image/map/SodRollCap.png", Texture.class);
    }

    public static Texture getSod1row() {
        return manager.get("image/map/sod1row.png", Texture.class);
    }

    public static Texture getGrowSoil() {
        return manager.get("image/map/GrowSoil.png", Texture.class);
    }

    public static Texture getLawnMower() {
        return manager.get("image/map/LawnMower.gif", Texture.class);
    }

    public static Texture getShadow() {
        return manager.get("image/map/plantshadow32.png", Texture.class);
    }

    public static Texture getPlantshadow8() {
        return manager.get("image/map/plantshadow8.png", Texture.class);
    }

    public static Texture getPeashooterbmp() {
        return manager.get("image/card/Peashooter.bmp", Texture.class);
    }

    public static Texture getPeashooter() {
        return manager.get("image/card/Peashooter.png", Texture.class);
    }

    public static Texture getPeashooterG() {
        return manager.get("image/card/PeashooterG.png", Texture.class);
    }

    public static Texture getSunFlower() {
        return manager.get("image/card/SunFlower.png", Texture.class);
    }

    public static Texture getSunFlowerG() {
        return manager.get("image/card/SunFlowerG.png", Texture.class);
    }

    public static Texture getSunBack() {
        return manager.get("image/card/SunBack.png", Texture.class);
    }

    public static Texture getFlagMeterEmpty() {
        return manager.get("image/Zombie/FlagMeterEmpty.png", Texture.class);
    }

    public static Texture getFlagMeterFull() {
        return manager.get("image/Zombie/FlagMeterFull.png", Texture.class);
    }

    public static Texture getFlagMeterParts1() {
        return manager.get("image/Zombie/FlagMeterParts1.png", Texture.class);
    }

    /* ******************************BitmatFont********************************* */
    public static BitmapFont getMyfont() {
        return manager.get("font/myfont.fnt", BitmapFont.class);
    }

    /* ******************************Animation********************************* */
    public static Animation zombieAimation,sunFlowerAimation;

    static {
        zombieAimation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("image/Zombie/Zombie.gif").read());
        sunFlowerAimation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("image/plants/SunFlower.gif").read());
    }
}
