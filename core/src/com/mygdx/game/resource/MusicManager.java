package com.mygdx.game.resource;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 * Created by Administrator on 2015/9/6.
 */
public class MusicManager {
    static Music backMusic;

    public MusicManager() {
        backMusic = Gdx.audio.newMusic(Gdx.files.internal("music/UraniwaNi_001.mp3"));
    }

    public static void play() {
        if (!backMusic.isPlaying()) {
            backMusic.setLooping(true);
            backMusic.play();
        }

    }

    public static void stop() {
        if (!backMusic.isPlaying()) {
            backMusic.stop();
        }
    }

}
