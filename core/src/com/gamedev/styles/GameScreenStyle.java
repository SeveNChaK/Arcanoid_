package com.gamedev.styles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class GameScreenStyle {
    private GameGraphics gameGraphics;

    public GameScreenStyle(GameGraphics gameGraphics) {
        this.gameGraphics = gameGraphics;
    }

    public BitmapFont font(){
        FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal("captureit.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter p = new FreeTypeFontGenerator.FreeTypeFontParameter();
        p.color = Color.BLACK;
        p.size = 50;
        BitmapFont font = gen.generateFont(p);

        return font;
    }
}
