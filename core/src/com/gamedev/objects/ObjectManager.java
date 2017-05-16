package com.gamedev.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.gamedev.screens.GameState;
import com.gamedev.styles.GameGraphics;
import com.gamedev.styles.ObjectStyle;

public class ObjectManager {
    private World world;
    private SpriteBatch batch;
    private GameState state;
    private ObjectStyle objectStyle;

    private Ball ball = new Ball();
    private Brick brick = new Brick();
    private Platform platform = new Platform();
    private GameBox gameBox = new GameBox();

    public ObjectManager(World world, GameGraphics gameGraphics, SpriteBatch batch, GameState state) {
        this.world = world;
        this.batch = batch;
        this.state = state;
        this.objectStyle = new ObjectStyle(gameGraphics);
    }

    public Body getBall() {
        return ball.creat(world, objectStyle);
    }

    public void renderBall(Body body, float cor){
        ball.render(body, batch, state, cor);
    }

    public Body getBrick(float preX, float preY, float space) {
        return brick.creat(world, objectStyle, preX, preY, space);
    }

    public void renderBrick(Body body){
        brick.render(body, batch);
    }

    public Body getPlatform() {
        return platform.creat(world, objectStyle);
    }

    public void renderPlatform(Body body){
        platform.render(body, batch, state);
    }

    public Body getGameBox() {
        return gameBox.creatGameBox(world);
    }
}
