package com.gamedev.LeaderBoard;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.gamedev.graphics.TableGraphics;

import java.util.ArrayList;
import java.util.List;

import static com.gamedev.Strings.*;

public class Line extends Actor{

    private Label lable;
    private List<Label> line;
    private List<String> data;

    private TableGraphics tableGraphics;

    public Line(List<String> data, TableGraphics tableGraphics){
        this.tableGraphics = tableGraphics;
        this.line = new ArrayList<Label>();
        this.data = data;

        Label.LabelStyle ls = new Label.LabelStyle();
        ls.font = tableGraphics.getFont();
        ls.background = tableGraphics.getSkin().getDrawable(text_field);

        lable = new Label(data.get(0), ls);
        lable.setAlignment(1, 1);
        lable.setWidth(500f);
        lable.setHeight(40f);
        line.add(lable);

        lable = new Label(data.get(1), ls);
        lable.setAlignment(0);
        lable.setWidth(100f);
        lable.setHeight(40f);
        line.add(lable);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        line.get(0).draw(batch, parentAlpha);
        line.get(1).draw(batch, parentAlpha);
    }

    @Override
    public void setPosition(float x, float y) {
        line.get(0).setPosition(x, y);
        line.get(1).setPosition(x + 510f, y);
    }
}
