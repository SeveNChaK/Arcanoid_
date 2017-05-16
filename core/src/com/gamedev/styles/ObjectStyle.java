package com.gamedev.styles;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class ObjectStyle {

    private GameGraphics gameGraphics;

    public ObjectStyle(GameGraphics gameGraphics) {
        this.gameGraphics = gameGraphics;
    }

    public Sprite ballStyle(){
        Sprite sprite = new Sprite(gameGraphics.getSkin().getSprite("ball"));
        return sprite;
    }

    public Sprite platformStyle(){
        Sprite sprite = new Sprite(gameGraphics.getSkin().getSprite("platform"));
        return sprite;
    }

    public Sprite brickStyle(){
        Sprite sprite = new Sprite(gameGraphics.getSkin().getSprite("block"));
        return sprite;
    }


}
