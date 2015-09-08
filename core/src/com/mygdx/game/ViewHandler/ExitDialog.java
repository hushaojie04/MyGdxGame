package com.mygdx.game.ViewHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.mygdx.game.ViewActor.LinearLayout;


/**
 * Created by Administrator on 2015/9/6.
 */
public class ExitDialog {
    Group dialog;
    TextButton ok, cancel;

    public ExitDialog() {
        dialog = new Group();
////为了让图片保持居中
        dialog.setX((Gdx.graphics.getWidth() - dialog.getWidth()) / 2f);
        dialog.setY((Gdx.graphics.getHeight() - dialog.getHeight()) / 2f);
        Texture[] textures = {
                new Texture(Gdx.files.internal("image/system/dialog_bottomleft.png")),
                new Texture(Gdx.files.internal("image/system/dialog_bottommiddle.png")),
                new Texture(Gdx.files.internal("image/system/dialog_bottomright.png")),
                new Texture(Gdx.files.internal("image/system/dialog_centerleft.png")),
                new Texture(Gdx.files.internal("image/system/dialog_centermiddle.png")),
                new Texture(Gdx.files.internal("image/system/dialog_centerright.png")),
                new Texture(Gdx.files.internal("image/system/dialog_topleft.png")),
                new Texture(Gdx.files.internal("image/system/dialog_topmiddle.png")),
                new Texture(Gdx.files.internal("image/system/dialog_topright.png"))
        };
        LinearLayout mLinearLayout = null;
        LinearLayout vertical = new LinearLayout(LinearLayout.VERTICAL);
        mLinearLayout = new LinearLayout(LinearLayout.HORIZONTAL);
        mLinearLayout.add(new Image(textures[0]));
        int wLen = (int) (Gdx.graphics.getWidth() * 0.5f - textures[0].getWidth() - textures[2].getWidth())
                / (int) textures[1].getWidth();
        int hLen = (int) (Gdx.graphics.getHeight() * 0.7f - textures[0].getHeight() - textures[3].getHeight())
                / (int) textures[6].getHeight();

        for (int i = 0; i < wLen; i++)
            mLinearLayout.add(new Image(textures[1]));
        mLinearLayout.add(new Image(textures[2]));
        vertical.add(mLinearLayout);
        for (int i = 0; i < hLen; i++) {
            mLinearLayout = new LinearLayout(LinearLayout.HORIZONTAL);
            mLinearLayout.add(new Image(textures[3]));
            for (int j = 0; j < wLen; j++)
                mLinearLayout.add(new Image(textures[4]));
            mLinearLayout.add(new Image(textures[5]));
            vertical.add(mLinearLayout);
        }
        mLinearLayout = new LinearLayout(LinearLayout.HORIZONTAL);
        mLinearLayout.add(new Image(textures[6]));
        for (int j = 0; j < wLen; j++)
            mLinearLayout.add(new Image(textures[7]));
        mLinearLayout.add(new Image(textures[8]));
        vertical.add(mLinearLayout);
        dialog.addActor(vertical);
        dialog.setWidth(vertical.getWidth());
        dialog.setHeight(vertical.getHeight());
        dialog.setX((Gdx.graphics.getWidth() - vertical.getWidth()) / 2);
        dialog.setY((Gdx.graphics.getHeight() - vertical.getHeight()) / 2);

        BitmapFont font = new BitmapFont(Gdx.files.internal("font/myfont.fnt"));
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
//        Sprite sprite = new Sprite(new Texture(Gdx.files.internal("image/Button.png")));
        Texture texture = new Texture(Gdx.files.internal("image/system/Button.png"));
        NinePatch ninePatch = new NinePatch(texture, 10, 10, 20, 20);
        NinePatchDrawable drawable = new NinePatchDrawable(ninePatch);
        textButtonStyle.down = drawable;
        textButtonStyle.up = drawable;
        textButtonStyle.font = font;
        textButtonStyle.fontColor = Color.GREEN;
        ok = new TextButton("确定", textButtonStyle);
        ok.setWidth((int) (dialog.getWidth() * 0.3f));
        ok.setHeight((int) (dialog.getHeight() * 0.25f * 0.5));
        ok.setX((dialog.getWidth() * 0.5f - ok.getWidth()) * 0.5f);
        ok.setY(dialog.getHeight() * 0.030f);
        ok.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Gdx.app.exit();
            }
        });
        //给ok这个按钮添加监听器

        cancel = new TextButton("取消", textButtonStyle);
        cancel.setWidth((int) (dialog.getWidth() * 0.3f));
        cancel.setHeight((int) (dialog.getHeight() * 0.25f * 0.5));
        cancel.setX(dialog.getWidth() * 0.5f + (dialog.getWidth() * 0.5f - ok.getWidth()) * 0.5f);
        cancel.setY(dialog.getHeight() * 0.030f);
        cancel.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                dialog.remove();
            }
        });
        dialog.addActor(ok);
        dialog.addActor(cancel);
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;
        labelStyle.fontColor = Color.YELLOW;
        Label mLabel = new Label("你确定\n想要退出游戏吗", labelStyle);
        mLabel.setFontScale(1.5f);
        mLabel.setPosition((dialog.getWidth() - mLabel.getWidth()) / 2 - 20, (dialog.getHeight() - mLabel.getHeight()) / 2);
        dialog.addActor(mLabel);
    }

    public void show(Stage stage) {
        stage.addActor(dialog);
    }

    public void hide() {
        dialog.remove();
    }

}
