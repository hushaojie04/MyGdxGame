package com.mygdx.game.Level;

import com.badlogic.gdx.utils.Json;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/9/7.
 */
public class Level {
    private int attackCount;
    private final List<String> plantsContent = new ArrayList<String>();
    private final List<ZombieContent> zombiesContent = new ArrayList<ZombieContent>();

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

    public List<ZombieContent> getZombiesContent() {
        return zombiesContent;
    }

    static class ZombieInfo {
        private String type;
        private String count;

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    static class ZombieContent {
        private int index;
        private final List<ZombieInfo> zombie = new ArrayList<ZombieInfo>();

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public List<ZombieInfo> getZombiesContent() {
            return zombie;
        }

    }
}
