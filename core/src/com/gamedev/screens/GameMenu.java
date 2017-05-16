package com.gamedev.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.gamedev.styles.ButtonStyle;
import com.gamedev.styles.GameGraphics;

import static com.gamedev.Constants.*;

public class GameMenu implements Screen{
    private Game game;
    private GameGraphics gameGraphics;
    private ButtonStyle buttonStyle;

    private Stage stage;
    private OrthographicCamera camera;

    private SpriteBatch batch;
    private Sprite background;

    private TextButton play, exit, records;

    public GameMenu(Game game, GameGraphics gameGraphics){
        this.gameGraphics = gameGraphics;
        this.game = game;
    }

    @Override
    public void show() {
        //camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //camera.setToOrtho(false, CAMERA_WIDTH, CAMERA_HEIGHT);
        //camera.position.set(new Vector3(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2,0));

        batch = new SpriteBatch();
        background = new Sprite(gameGraphics.getSkin().getSprite("menu_background"));
        background.setBounds(0, 0, GAME_WIDTH, GAME_HEIGHT);

        buttonStyle = new ButtonStyle(gameGraphics);

        play = new TextButton("PLAY", buttonStyle.playStyle());
        play.setStyle(buttonStyle.pauseStyle());
        play.setBounds(GAME_WIDTH / 2 - 100, GAME_HEIGHT / 2 - 25, 200, 50);

        exit = new TextButton("EXIT", buttonStyle.exitStyle());
        exit.setBounds(GAME_WIDTH / 2 - 100, GAME_HEIGHT / 2 - 50 * 4 - 10, 200, 50);

        records = new TextButton("RECORDS", buttonStyle.recordStyle());
        records.setBounds(GAME_WIDTH / 2 - 100, GAME_HEIGHT / 2 - 50 * 2 - 10, 200, 50);

        //stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera));
        stage = new Stage();
        stage.addActor(play);
        stage.addActor(exit);
        stage.addActor(records);
        stage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (play.isChecked()) {
                    game.setScreen(new GameScreen(game, gameGraphics));
                }
                if (exit.isChecked()) {
                    dispose();
                }
                if (records.isChecked()){
                    game.setScreen(new GameRecords(game, gameGraphics));
                }
                super.clicked(event, x, y);
            }
        });
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        //camera.update();
        //batch.setProjectionMatrix(camera.combined);

        Gdx.gl.glClearColor(0.7f, 0.7f, 0.7f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        background.draw(batch);
        //batch.draw(texture, 0 ,0);
        batch.end();
        stage.act(delta);
        stage.draw();
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
        batch.dispose();
        play.clear();
        exit.clear();
        records.clear();
        stage.dispose();
    }

    @Override
    public void dispose() {
        batch.dispose();
        background.getTexture().dispose();
        play.clear();
        exit.clear();
        records.clear();
        stage.dispose();
        System.exit(0);
    }
}
