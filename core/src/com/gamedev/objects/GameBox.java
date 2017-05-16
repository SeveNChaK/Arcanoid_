package com.gamedev.objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import static com.gamedev.Constants.*;

public class GameBox {

    public GameBox() {
    }

    public Body creatGameBox(World world){
        Body gameBox;

        BodyDef bDef = new BodyDef();
        bDef.type = BodyDef.BodyType.StaticBody;
        bDef.position.set(0,0);
        gameBox = world.createBody(bDef);

        FixtureDef fDef = new FixtureDef();
        ChainShape shape = new ChainShape();
        shape.createChain(new Vector2[]{new Vector2(0, 0), new Vector2(0, CAMERA_HEIGHT),
                new Vector2(CAMERA_WIDTH, CAMERA_HEIGHT), new Vector2(CAMERA_WIDTH, 0)});
        fDef.shape = shape;
        gameBox.createFixture(fDef).setUserData("GAMEBOX");
        shape.dispose();

        return gameBox;
    }
}
