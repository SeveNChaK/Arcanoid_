package com.gamedev;

import com.badlogic.gdx.Gdx;

public class Constants {
    public static final int GAME_WIDTH = Gdx.graphics.getWidth();
    public static final int GAME_HEIGHT = Gdx.graphics.getHeight();
    public static final String GAME_TITLE = "Arcanoid_V2.0";

    public static final float CAMERA_WIDTH = 800f;
    public static final float CAMERA_HEIGHT = 600f;
    public static final float CORRECT_IN_METERS_X = (float)(GAME_WIDTH)/CAMERA_WIDTH;
    public static final float CORRECT_IN_METERS_Y = (float)(GAME_HEIGHT)/CAMERA_HEIGHT;

    public static final float BRICK_HEIGHT = 15f;
    public static final float BRICK_WIDTH = 37.5f;

    public static final float PLATFORM_HEIGHT = 8f;
    public static final float PLTFORM_WIDTH = 75f;
    public static final float PLATFORM_SPEED = 10;
    public static final float PLATFORM_START_POSITION_X = 50;
    public static final float PLATFORM_START_POSITION_Y = 20;

    public static final float BALL_RADIUS = 9;
    public static final float BALL_START_POSITION_X = 50;
    public static final float BALL_START_POSITION_Y = 40;
    public static final float BALL_SPEED = 100000f;
}
