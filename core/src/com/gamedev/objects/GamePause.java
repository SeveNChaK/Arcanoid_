package com.gamedev.objects;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.gamedev.screens.GameMenu;
import com.gamedev.screens.GameScreen;
import com.gamedev.styles.GameGraphics;
import com.gamedev.styles.GameOverStyle;

import static com.gamedev.screens.GameState.PLAY;

public class GamePause {
    private Game game;
    private GameGraphics gameGraphics;
    private GameScreen gameScreen;

    private GameOverStyle gameOverStyle;

    private Dialog dialog;

    public GamePause(GameScreen gameScreen, Game game, GameGraphics gameGraphics) {
        this.game = game;
        this.gameGraphics = gameGraphics;
        this.gameScreen = gameScreen;
    }

    public void show(Stage stage) {
        gameOverStyle = new GameOverStyle(gameGraphics);

        dialog = new Dialog("", gameOverStyle.windowStyle()) {

            @Override
            protected void result(Object object) {
                if ((Integer) object == 1){
                    gameScreen.setState(PLAY);
                }

                if ((Integer) object == 0){
                    hide();
                    game.setScreen(new GameScreen(game, gameGraphics));
                }

                if ((Integer) object == -1){
                    game.setScreen(new GameMenu(game, gameGraphics));
                }
            }
        };
        dialog.button("Menu", -1, gameOverStyle.textButtonStyle());
        dialog.button("Restart", 0, gameOverStyle.textButtonStyle());
        dialog.button("Resume", 1, gameOverStyle.textButtonStyle());
        dialog.text("Pause", gameOverStyle.labelStyle());
        dialog.show(stage);
    }

    public void render(float delta) {
        gameScreen.getStage().act(delta);
        gameScreen.getStage().draw();
        gameScreen.getBatch().begin();
        gameScreen.getBatch().end();
    }
}
