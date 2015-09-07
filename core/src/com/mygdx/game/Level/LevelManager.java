package com.mygdx.game.Level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/9/7.
 */
public class LevelManager {
    private int level;
    private int maxLevel;
    private int groupCount = 0;
    private Map<Class, Integer> theTypesOfZombies = new HashMap<Class, Integer>();
    private List<Class> theTypesOfPlants = new ArrayList<Class>();
    private static LevelManager mLevelManager = new LevelManager();
    private Level mLevelInfo;

    public static LevelManager getManager() {
        return mLevelManager;
    }

    public void setLevel(int level) {
        File file = new File("json/level1.json");
        Json json = new Json();
        mLevelInfo = json.fromJson(Level.class, Gdx.files.internal("json/level1.json"));
    }

    public Level getLevel() {
        return mLevelInfo;
    }
}
