package com.gamedev.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.gamedev.LeaderBoard.Board;
import com.gamedev.styles.ButtonStyle;
import com.gamedev.styles.GameGraphics;
import com.gamedev.utils.JsonUtils;

public class GameRecords implements Screen {
    private Game game;
    private GameGraphics gameGraphics;
    private OrthographicCamera camera;

    private SpriteBatch batch;

    private ButtonStyle buttonStyle;

    private Board board;

    private Stage stage;

    private TextButton cancel, delete;

    public GameRecords(Game game, GameGraphics gameGraphics){
        this.game = game;
        this.gameGraphics = gameGraphics;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(false, 800, 600);

        batch = new SpriteBatch();

        buttonStyle = new ButtonStyle(gameGraphics);

        stage = new Stage(new FitViewport(800, 600, camera));

        board = new Board();

        delete = new TextButton("DELETE ALL", buttonStyle.deleteStyle());
        delete.setBounds(350, 30, 117, 32);

        cancel = new TextButton("CANCEL", buttonStyle.cancelStyle());
        cancel.setBounds(50, 30, 117, 32);

        stage.addActor(board);
        stage.addActor(delete);
        stage.addActor(cancel);
        stage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (cancel.isChecked()) {
                    JsonUtils.saveRecords(board.getData());
                    game.setScreen(new GameMenu(game, gameGraphics));
                }
                if (delete.isChecked()){
                    board.getData().clear();
                    board.getLines().clear();
                }
                super.clicked(event, x, y);
            }
        });
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        Gdx.gl.glClearColor(0.7f, 0.7f, 0.7f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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
        dispose();

    }

    @Override
    public void dispose() {
        batch.dispose();
        cancel.clear();
        delete.clear();
        stage.dispose();
    }
}
