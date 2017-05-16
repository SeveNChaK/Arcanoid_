package com.gamedev.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.gamedev.styles.ObjectStyle;

import static com.gamedev.Constants.*;

public class Brick {

    public Brick() {
    }

    public Body creat(World world, ObjectStyle objectStyle, float preX, float preY, float space){
        Body brick;
        Sprite brickSprite;

        BodyDef bDef = new BodyDef();
        bDef.type = BodyDef.BodyType.StaticBody;
        bDef.position.set(new Vector2(preX + space, preY));
        brick = world.createBody(bDef);

        FixtureDef fDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(BRICK_WIDTH, BRICK_HEIGHT);
        fDef.shape = shape;
        brick.createFixture(fDef).setUserData("BRICK");
        shape.dispose();

        brickSprite = new Sprite(objectStyle.brickStyle());
        brickSprite.setBounds(CORRECT_IN_METERS_X * (brick.getPosition().x - BRICK_WIDTH),
                CORRECT_IN_METERS_Y * (brick.getPosition().y - BRICK_HEIGHT),
                (CORRECT_IN_METERS_X * BRICK_WIDTH) * 2, (CORRECT_IN_METERS_Y * BRICK_HEIGHT) * 2);

        brick.setUserData(brickSprite);
        return brick;
    }

    public void render(Body body, SpriteBatch batch){
        ((Sprite)body.getUserData()).setBounds(CORRECT_IN_METERS_X * (body.getPosition().x - BRICK_WIDTH),
                CORRECT_IN_METERS_Y * (body.getPosition().y - BRICK_HEIGHT),
                (CORRECT_IN_METERS_X * BRICK_WIDTH)*2,(CORRECT_IN_METERS_Y * BRICK_HEIGHT) * 2);
        batch.begin();
        ((Sprite)body.getUserData()).draw(batch);
        batch.end();
    }
}
