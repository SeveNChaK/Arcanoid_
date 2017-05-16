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

public class Platform {

    public Platform() {
    }

    public Body creat(World world, ObjectStyle objectStyle) {
        Body platform;
        Sprite platformSprite;

        BodyDef bDef = new BodyDef();
        bDef.type = BodyDef.BodyType.StaticBody;
        bDef.position.set(PLATFORM_START_POSITION_X, PLATFORM_START_POSITION_Y);
        platform = world.createBody(bDef);

        FixtureDef fDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(PLTFORM_WIDTH, PLATFORM_HEIGHT);
        fDef.shape = shape;
        platform.createFixture(fDef).setUserData("PLATFORM");
        shape.dispose();

        platformSprite = new Sprite(objectStyle.platformStyle());
        platformSprite.setBounds(CORRECT_IN_METERS_X * (platform.getPosition().x - PLTFORM_WIDTH),
                CORRECT_IN_METERS_Y * (platform.getPosition().y - PLATFORM_HEIGHT),
                (CORRECT_IN_METERS_X * PLTFORM_WIDTH) * 2, (CORRECT_IN_METERS_Y * PLATFORM_HEIGHT) * 2);

        platform.setUserData(platformSprite);

        return platform;
    }

    public void render(Body body, SpriteBatch batch, GameState state){
        if (state ==PLAY){
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                reposition(body);
            }
            ((Sprite)body.getUserData()).setBounds(CORRECT_IN_METERS_X * (body.getPosition().x - PLTFORM_WIDTH),
                    CORRECT_IN_METERS_Y * (body.getPosition().y - PLATFORM_HEIGHT),
                    (CORRECT_IN_METERS_X * PLTFORM_WIDTH)*2,(CORRECT_IN_METERS_Y * PLATFORM_HEIGHT) * 2);
        }

        batch.begin();
        ((Sprite)body.getUserData()).draw(batch);
        batch.end();
    }

    private void reposition(Body body) {
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            body.setTransform(body.getPosition().x + PLATFORM_SPEED, body.getPosition().y, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            body.setTransform(body.getPosition().x - PLATFORM_SPEED, body.getPosition().y, 0);
        }
        if (body.getPosition().x > 800 - PLTFORM_WIDTH) {
            body.setTransform(800 - PLTFORM_WIDTH, body.getPosition().y, 0);
        }
        if (body.getPosition().x < 0 + PLTFORM_WIDTH) {
            body.setTransform(0 + PLTFORM_WIDTH, body.getPosition().y, 0);
        }
    }
}
