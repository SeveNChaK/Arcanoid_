package com.gamedev.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.gamedev.objects.*;
import com.gamedev.styles.GameGraphics;
import com.gamedev.styles.GameScreenStyle;
import com.gamedev.utils.JsonUtils;
import com.gamedev.utils.MyContactListener;


import static com.badlogic.gdx.math.MathUtils.random;
import static com.gamedev.Constants.*;
import static com.gamedev.screens.GameState.*;

import java.util.ArrayList;
import java.util.List;

public class GameScreen implements Screen {
    private Game game;
    private World world;
    private Stage stage;
    private Box2DDebugRenderer renderer;
    private GameGraphics gameGraphics;

    private GameScreenStyle gameScreenStyle;

    private OrthographicCamera camera;

    private Body platform, ball, gameBox;
    private List<Body> bricks = new ArrayList<Body>();
    private GameOver gameOver;

    private ObjectManager objectManager;

    private SpriteBatch batch;
    private Sprite background;

    private MyContactListener myCL;

    private GameState state = PLAY;

    private long count = 0;
    private long pastCount = count;
    private int countBrickInLine = 0;

    //private int bonusSpeed = 0;

    private List<List<String>> records = new ArrayList<List<String>>();

    private BitmapFont font;


    public GameScreen(Game game, GameGraphics gameGraphics) {
        this.gameGraphics = gameGraphics;
        this.game = game;
    }

    @Override
    public void show() {
        world = new World(new Vector2(0, 0), true);
        myCL = new MyContactListener(world);
        world.setContactListener(myCL);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, CAMERA_WIDTH, CAMERA_HEIGHT);
        //camera.position.set(new Vector3(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2,0));

        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera));
        //stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        batch = new SpriteBatch();
        renderer = new Box2DDebugRenderer();

        background = new Sprite(gameGraphics.getSkin().getSprite("background"));
        background.setBounds(0, 0, GAME_WIDTH, GAME_HEIGHT);

        objectManager = new ObjectManager(world, gameGraphics, batch, state);

        ball = objectManager.getBall();
        platform = objectManager.getPlatform();
        gameBox = objectManager.getGameBox();

        float preY = 0;
        for (int j = 0; j < 6; j++) {
            int countBrickInLine = random(6, 10);
            float space = (float) ((80 - (countBrickInLine * BRICK_WIDTH * 2)) / (countBrickInLine + 1));
            List<Body> brickLine = new ArrayList<Body>();
            float preX = space + BRICK_WIDTH;
            if (preY == 0) {
                brickLine.add(objectManager.getBrick(preX, 38.5f, 0f));
                preY = 38.5f + 4f;
            } else {
                brickLine.add(objectManager.getBrick(preX, preY, 0f));
                preY = preY + 4f;
            }
            for (int i = 1; i < countBrickInLine; i++) {
                brickLine.add(
                        objectManager.getBrick(brickLine.get(i - 1).getPosition().x,
                                brickLine.get(i - 1).getPosition().y, space + BRICK_WIDTH * 2)
                );
            }
            bricks.addAll(brickLine);
        }

        gameOver = new GameOver(this);
        gameOver.show(stage);

        records = JsonUtils.loadRecords("saves.json");

        gameScreenStyle = new GameScreenStyle(gameGraphics);

        font = gameScreenStyle.font();

    }

    @Override
    public void render(float delta) {
        if (myCL.getRemovableBrick() != null) {
            world.destroyBody(myCL.getRemovableBrick());
            bricks.remove(myCL.getRemovableBrick());
            myCL.setBody(null);
            count++;
        }

        if (count - pastCount == countBrickInLine) {
            pastCount += countBrickInLine;

            countBrickInLine = random(6, 10);
            float space = (float) ((80 - (countBrickInLine * BRICK_WIDTH * 2)) / (countBrickInLine + 1));
            List<Body> brickLine = new ArrayList<Body>();
            float preX = space + BRICK_WIDTH;
            brickLine.add(objectManager.getBrick(preX, CAMERA_HEIGHT + 1f + BRICK_HEIGHT, 0f));
            for (int i = 1; i < countBrickInLine; i++) {
                brickLine.add(
                        objectManager.getBrick(brickLine.get(i - 1).getPosition().x,
                                brickLine.get(i - 1).getPosition().y, space + BRICK_WIDTH * 2)
                );
            }
            bricks.addAll(brickLine);
            for (Body b : bricks) {
                b.setTransform(b.getPosition().x, b.getPosition().y - 4f, b.getAngle());
            }
            BALL_SPEED++;
        }

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render(world, camera.combined);
        world.step(delta, 6, 2);
        camera.update();

        batch.begin();
        background.draw(batch);
        batch.end();

        objectManager.renderBall(ball, myCL.getCorrect());
        objectManager.renderPlatform(platform);
        for (Body b : bricks) {
            objectManager.renderBrick(b);
        }
        batch.begin();
        font.draw(batch, "" + count, 4, 550);
        batch.end();

        if (ball.getPosition().y < -2  && state == PLAY ) {
            ball.setLinearVelocity(0, 0);
            ball.setAngularVelocity(0);
            state = GAME_OVER;
            objectManager.setState(state);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
//            state = PAUSE;
//            ball.setLinearVelocity(0, 0);
//            ball.setAngularVelocity(0);
//            objectManager.setState(state);
            System.out.println(ball.getLinearVelocity());
        }

        if (state == GAME_OVER) {
            gameOver.render(this, delta);
        }

//        if (Gdx.input.isKeyJustPressed(Input.Keys.S)){
//            System.out.println(ball.getLinearVelocity());
//        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        JsonUtils.saveRecords(records);
        world.destroyBody(ball);
        world.destroyBody(platform);
        for (int i = 0; i < bricks.size(); i++)
            world.destroyBody(bricks.get(i));
        world.destroyBody(gameBox);
        renderer.dispose();
        batch.dispose();
        world.dispose();
        BALL_SPEED = 20f;
    }

    public long getCount() {
        return count;
    }

    public List<List<String>> getRecords() {
        return records;
    }

    public Stage getStage() {
        return stage;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public Game getGame() {
        return game;
    }

    public GameGraphics getGameGraphics() {
        return gameGraphics;
    }
}
