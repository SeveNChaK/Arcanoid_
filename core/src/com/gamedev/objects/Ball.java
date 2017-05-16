package com.gamedev.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;
import com.gamedev.screens.GameState;
import com.gamedev.styles.ObjectStyle;

import static com.gamedev.screens.GameState.PLAY;
import static com.gamedev.Constants.*;

public class Ball {

    private boolean clickSpace = false;

    public Ball() {
    }

    public Body creat(World world, ObjectStyle objectStyle){
        Body ball;
        Sprite ballSprite;

        BodyDef bDef = new BodyDef();
        bDef.type = BodyDef.BodyType.DynamicBody;
        bDef.position.set(BALL_START_POSITION_X, BALL_START_POSITION_Y);
        ball = world.createBody(bDef);

        FixtureDef fDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(BALL_RADIUS);
        fDef.shape = shape;
        fDef.restitution = 1;
        fDef.friction = 0;
        ball.createFixture(fDef).setUserData("BALL");
        shape.dispose();

        ballSprite = new Sprite(objectStyle.ballStyle());
        ballSprite.setBounds(CORRECT_IN_METERS_X * (ball.getPosition().x - BALL_RADIUS),
                CORRECT_IN_METERS_Y * (ball.getPosition().y - BALL_RADIUS),
                (CORRECT_IN_METERS_X * BALL_RADIUS)*2,(CORRECT_IN_METERS_Y * BALL_RADIUS) * 2);

        ball.setUserData(ballSprite);

        return ball;
    }

    public void render(Body body, SpriteBatch batch, GameState state, float cor){
        if (state == PLAY){
            if ((Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) && !clickSpace) {
                reposition(body);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                if (!clickSpace) {
                    body.setAngularVelocity(10);
                    body.setLinearVelocity(BALL_SPEED, BALL_SPEED);
                }
                clickSpace = true;
            }
            if (clickSpace) correct(body, cor);
            ((Sprite)body.getUserData()).setBounds(CORRECT_IN_METERS_X * (body.getPosition().x - BALL_RADIUS),
                    CORRECT_IN_METERS_Y * (body.getPosition().y - BALL_RADIUS),
                    (CORRECT_IN_METERS_X * BALL_RADIUS)*2,(CORRECT_IN_METERS_Y * BALL_RADIUS) * 2);
        }

        batch.begin();
        ((Sprite)body.getUserData()).draw(batch);
        batch.end();
    }

    private void reposition(Body body) {
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if (body.getPosition().x + PLATFORM_SPEED <= 800 - PLTFORM_WIDTH) {
                body.setTransform(body.getPosition().x + PLATFORM_SPEED, body.getPosition().y, 0);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if (body.getPosition().x > 0 + PLTFORM_WIDTH) {
                body.setTransform(body.getPosition().x - PLATFORM_SPEED, body.getPosition().y, 0);
            }
        }
    }

    private void correct(Body body, float cor) {
        int correctX = 1;
        int correctY = 1;
        if (Math.abs(body.getLinearVelocity().x) != BALL_SPEED + cor/2 || Math.abs(body.getLinearVelocity().y) != BALL_SPEED - cor/2) {
            if (body.getLinearVelocity().x < 0) correctX = -1;
            else correctX = 1;

            if (body.getLinearVelocity().y < 0) correctY = -1;
            else correctY = 1;
            body.setLinearVelocity((BALL_SPEED + cor/2) * correctX, (BALL_SPEED - cor/2) * correctY);
        }
    }

}
