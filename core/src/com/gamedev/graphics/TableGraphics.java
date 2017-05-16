package com.gamedev.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class TableGraphics {

    private BitmapFont font;

    private Skin skin;

    public TableGraphics(){
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("Arcanoid.atlas"));
        skin = new Skin();
        skin.addRegions(atlas);
    }

    public void load(){
        FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal("captureit.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter p = new FreeTypeFontGenerator.FreeTypeFontParameter();
        p.color = Color.BLACK;
        p.size = 30;
        font = gen.generateFont(p);
    }

    public Skin getSkin() {
        return skin;
    }

    public BitmapFont getFont() {
        return font;
    }
}
