package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.resource.Res;
import com.mygdx.game.screen.LoadingScreen;
import com.mygdx.game.screen.MenuScreen;


public class MyGdxGame extends Game {
    Screen mLoadingScreen;
    Screen mMenuScreen;
    @Override
    public void create() {
        Gdx.input.setCatchBackKey(true);
        mLoadingScreen = new LoadingScreen(this);
        mMenuScreen = new MenuScreen(this);
        setScreen(mLoadingScreen);
    }

    public void openMenuScreen() {
        setScreen(mMenuScreen);
    }

    @Override
    public void dispose() {
        super.dispose();
        Res.unload();
    }
}
