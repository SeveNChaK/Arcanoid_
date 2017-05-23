package com.gamedev.utils;

import com.badlogic.gdx.physics.box2d.*;

public class MyContactListener implements ContactListener {
    World world;
    private Body body = null;
    private long count = 0;

    private float correct = 0;

    public MyContactListener(World world) {
        super();
        this.world = world;
    }

    @Override
    public void beginContact(Contact contact) {
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        if (contact.getFixtureA() != null && contact.getFixtureA().getUserData() != null && contact.getFixtureA().getUserData().equals("BRICK"))
        {
            body = contact.getFixtureA().getBody();
        }

        if (contact.getFixtureB() != null && contact.getFixtureB().getUserData() != null && contact.getFixtureB().getUserData().equals("BRICK")) {
            body = contact.getFixtureB().getBody();
        }


        if (contact.getFixtureA() != null && contact.getFixtureA().getUserData() != null && contact.getFixtureA().getUserData().equals("PLATFORM"))
        {
            correct = Math.abs(contact.getFixtureA().getBody().getPosition().x - contact.getFixtureB().getBody().getPosition().x) * 2;
        }

        if (contact.getFixtureB() != null && contact.getFixtureB().getUserData() != null && contact.getFixtureB().getUserData().equals("PLATFORM")) {
            correct = Math.abs(contact.getFixtureA().getBody().getPosition().x - contact.getFixtureB().getBody().getPosition().x) * 2;
        }
    }

    public Body getRemovableBrick(){
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public float getCorrect() {
        return correct;
    }

    public void setCorrect(float correct) {
        this.correct = correct;
    }
}
