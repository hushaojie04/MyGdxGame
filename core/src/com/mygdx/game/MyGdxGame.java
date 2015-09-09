package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.resource.MusicManager;
import com.mygdx.game.resource.Res;
import com.mygdx.game.screen.GameScreen;
import com.mygdx.game.screen.LoadingScreen;
import com.mygdx.game.screen.MenuScreen;


public class MyGdxGame extends Game {
    Screen mLoadingScreen;
    Screen mMenuScreen;
    GameScreen mGameScreen;

    @Override
    public void create() {
        Gdx.input.setCatchBackKey(true);
        mLoadingScreen = new LoadingScreen(this);
        mMenuScreen = new MenuScreen(this);
//        mGameScreen = new GameScreen(this);
        setScreen(mLoadingScreen);
    }

    public void openMenuScreen() {
        setScreen(mMenuScreen);
    }

    public void startGameScreen() {
        setScreen(new GameScreen(this));
    }

    @Override
    public void dispose() {
        super.dispose();
        Res.unload();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void pause() {
        super.pause();

    }
}
