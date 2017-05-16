package com.gamedev.styles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class GameGraphics {

    private Skin skin;

    public GameGraphics() {
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("Arcanoid.atlas"));
        skin = new Skin();
        skin.addRegions(atlas);
    }

    public Skin getSkin() {
        return skin;
    }
}
