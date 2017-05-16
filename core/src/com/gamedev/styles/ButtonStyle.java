package com.gamedev.styles;

import com.badlogic.gdx.*;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.gamedev.styles.*;

public class ButtonStyle {

    private GameGraphics gameGraphics;

    public ButtonStyle(GameGraphics gameGraphics) {
        this.gameGraphics = gameGraphics;
    }

    public TextButton.TextButtonStyle playStyle(){
        FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal("captureit.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter p = new FreeTypeFontGenerator.FreeTypeFontParameter();
        p.color = Color.BLACK;
        p.size = 30;
        BitmapFont font = gen.generateFont(p);

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.up = gameGraphics.getSkin().getDrawable("up");
        style.checked = gameGraphics.getSkin().getDrawable("unchecked");
        style.down = gameGraphics.getSkin().getDrawable("down");
        style.font = font;


        return style;
    }

    public TextButton.TextButtonStyle recordStyle(){
        FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal("captureit.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter p = new FreeTypeFontGenerator.FreeTypeFontParameter();
        p.color = Color.BLACK;
        p.size = 30;
        BitmapFont font = gen.generateFont(p);

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.up = gameGraphics.getSkin().getDrawable("up");
        style.checked = gameGraphics.getSkin().getDrawable("unchecked");
        style.down = gameGraphics.getSkin().getDrawable("down");
        style.font = font;


        return style;
    }

    public TextButton.TextButtonStyle settingStyle(){
        FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal("captureit.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter p = new FreeTypeFontGenerator.FreeTypeFontParameter();
        p.color = Color.BLACK;
        p.size = 30;
        BitmapFont font = gen.generateFont(p);

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.up = gameGraphics.getSkin().getDrawable("up");
        style.checked = gameGraphics.getSkin().getDrawable("unchecked");
        style.down = gameGraphics.getSkin().getDrawable("down");
        style.font = font;


        return style;
    }

    public TextButton.TextButtonStyle exitStyle(){
        FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal("captureit.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter p = new FreeTypeFontGenerator.FreeTypeFontParameter();
        p.color = Color.BLACK;
        p.size = 30;
        BitmapFont font = gen.generateFont(p);

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.up = gameGraphics.getSkin().getDrawable("up");
        style.checked = gameGraphics.getSkin().getDrawable("unchecked");
        style.down = gameGraphics.getSkin().getDrawable("down");
        style.font = font;


        return style;
    }

    public TextButton.TextButtonStyle cancelStyle(){
        FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal("captureit.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter p = new FreeTypeFontGenerator.FreeTypeFontParameter();
        p.color = Color.BLACK;
        p.size = 30;
        BitmapFont font = gen.generateFont(p);

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.up = gameGraphics.getSkin().getDrawable("up");
        style.checked = gameGraphics.getSkin().getDrawable("unchecked");
        style.down = gameGraphics.getSkin().getDrawable("down");
        style.font = font;


        return style;
    }

    public TextButton.TextButtonStyle deleteStyle(){
        FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal("captureit.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter p = new FreeTypeFontGenerator.FreeTypeFontParameter();
        p.color = Color.BLACK;
        p.size = 30;
        BitmapFont font = gen.generateFont(p);

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.up = gameGraphics.getSkin().getDrawable("up");
        style.checked = gameGraphics.getSkin().getDrawable("unchecked");
        style.down = gameGraphics.getSkin().getDrawable("down");
        style.font = font;


        return style;
    }

    public TextButton.TextButtonStyle pauseStyle(){
        FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal("captureit.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter p = new FreeTypeFontGenerator.FreeTypeFontParameter();
        p.color = Color.BLACK;
        p.size = 30;
        BitmapFont font = gen.generateFont(p);

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.up = gameGraphics.getSkin().getDrawable("up");
        style.checked = gameGraphics.getSkin().getDrawable("unchecked");
        style.down = gameGraphics.getSkin().getDrawable("down");
        style.font = font;


        return style;
    }
}
