package com.gamedev.objects;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.gamedev.screens.GameMenu;
import com.gamedev.screens.GameScreen;
import com.gamedev.styles.GameGraphics;
import com.gamedev.styles.GameOverStyle;

import java.util.ArrayList;

public class GameOver {
    private GameScreen gameScreen;

    private final StringBuilder stringBuilder = new StringBuilder();

    private GameOverStyle gameOverStyle;

    private boolean click = false;

    private Dialog dialog;
    private TextField textField;

    public GameOver(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    public void show(Stage stage) {
        gameOverStyle = new GameOverStyle(gameScreen.getGameGraphics());

        dialog = new Dialog("", gameOverStyle.windowStyle()) {

            @Override
            protected void result(Object object) {
                if ((Boolean) object) {
                    hide();
                    save();
                    gameScreen.getGame().setScreen(new GameScreen(gameScreen.getGame(), gameScreen.getGameGraphics()));
                } else {
                    save();
                    gameScreen.getGame().setScreen(new GameMenu(gameScreen.getGame(), gameScreen.getGameGraphics()));
                }
            }
        };
        dialog.button("Menu", false, gameOverStyle.textButtonStyle());
        dialog.button("Restart", true, gameOverStyle.textButtonStyle());
        dialog.text("You lose", gameOverStyle.labelStyle());
        dialog.getButtonTable().setVisible(false);
        dialog.show(stage);

        textField = new TextField("", gameOverStyle.textFieldStyle());
        textField.setBounds(200, 220, 400, 60);
        textField.setMaxLength(15);
        textField.setAlignment(1);
        textField.setMessageText("Write name");
        textField.setTextFieldListener(new TextField.TextFieldListener() {
            @Override
            public void keyTyped(TextField textField, char c) {
                stringBuilder.append(c);
            }
        });
        stage.addActor(textField);
    }

    public void render(GameScreen gameScreen, float delta) {
        gameScreen.getStage().act(delta);
        gameScreen.getStage().draw();
        gameScreen.getBatch().begin();
        textField.draw(gameScreen.getBatch(), 1f);
        gameScreen.getBatch().end();
        if (!textField.getText().equals("")) {
            dialog.getButtonTable().setVisible(true);
        }
    }

    private void save() {
        java.util.List<String> l = new ArrayList<String>();
        if (textField.getText().equals("")) {
            textField.setText("No name");
        }
        l.add(textField.getText());
        l.add("" + gameScreen.getCount());
        System.out.println(textField.getText());
        gameScreen.getRecords().add(l);
    }
}
