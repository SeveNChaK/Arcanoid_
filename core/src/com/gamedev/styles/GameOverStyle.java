package com.gamedev.styles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

import static com.gamedev.Strings.*;

public class GameOverStyle {

    private GameGraphics gameGraphics;

    public GameOverStyle(GameGraphics gameGraphics) {
        this.gameGraphics = gameGraphics;
    }

    public TextField.TextFieldStyle textFieldStyle(){
        FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal(font));
        FreeTypeFontGenerator.FreeTypeFontParameter p = new FreeTypeFontGenerator.FreeTypeFontParameter();
        p.color = Color.BLACK;
        p.size = 50;
        BitmapFont font = gen.generateFont(p);

        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.font = font;
        textFieldStyle.fontColor = Color.BLUE;
        textFieldStyle.background = gameGraphics.getSkin().getDrawable(text_field); //
        textFieldStyle.cursor = gameGraphics.getSkin().getDrawable(text_pointer);

        return textFieldStyle;
    }

    public Window.WindowStyle windowStyle(){
        FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal(font));
        FreeTypeFontGenerator.FreeTypeFontParameter p = new FreeTypeFontGenerator.FreeTypeFontParameter();
        p.color = Color.BLACK;
        p.size = 30;
        BitmapFont font = gen.generateFont(p);

        Window.WindowStyle windowStyle = new Window.WindowStyle();
        windowStyle.titleFont = font;
        windowStyle.background = gameGraphics.getSkin().getDrawable(dialog); //

        return windowStyle;
    }

    public Label.LabelStyle labelStyle(){
        FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal(font));
        FreeTypeFontGenerator.FreeTypeFontParameter p = new FreeTypeFontGenerator.FreeTypeFontParameter();
        p.color = Color.BLACK;
        p.size = 70;
        BitmapFont font = gen.generateFont(p);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;

        return labelStyle;
    }

    public TextButton.TextButtonStyle textButtonStyle(){
        FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal(font));
        FreeTypeFontGenerator.FreeTypeFontParameter p = new FreeTypeFontGenerator.FreeTypeFontParameter();
        p.color = Color.BLACK;
        p.size = 30;
        BitmapFont font = gen.generateFont(p);

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.up = gameGraphics.getSkin().getDrawable(up);
        style.over = gameGraphics.getSkin().getDrawable(over);
        style.down = gameGraphics.getSkin().getDrawable(down);
        style.font = font;

        return style;
    }
}
