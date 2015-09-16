package com.mygdx.game.Level;

import com.badlogic.gdx.utils.Json;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/9/7.
 */
public class Level {
    private int level;
    private int attackCount;
    private final List<String> plantsContent = new ArrayList<String>();
    private final List<ZombieInfo> zombiesContent = new ArrayList<ZombieInfo>();

    public Level() {
    }

    public int getAttackCount() {
        return attackCount;
    }

    public void setAttackCount(int attackCount) {
        this.attackCount = attackCount;
    }

    public List<String> getPlantsContent() {
        return plantsContent;
    }

    public List<ZombieInfo> getZombiesContent() {
        return zombiesContent;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public static class ZombieInfo {
        private String type;
        private int count;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }


}
