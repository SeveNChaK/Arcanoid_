package com.gamedev.LeaderBoard;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.gamedev.graphics.TableGraphics;
import com.gamedev.utils.JsonUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Board extends Actor{

    private List<List<String>> data;
    private List<Line> lines;

    private TableGraphics tablrGraphics;

    public Board(){
        this.tablrGraphics = new TableGraphics();
        tablrGraphics.load();
        this.data = new ArrayList<List<String>>();
        this.lines = new ArrayList<Line>();

        data = JsonUtils.loadRecords("saves.json");
        sortMaxToMin();
        if (data.size()>10){
            data = data.subList(0, 10);
        }

        float x = 90f;
        float y = 550f;
        float correct = 0;

        for (int i = 0; i<data.size(); i++){
            Line l = new Line(data.get(i), tablrGraphics);
            l.setPosition(x, y-correct);
            lines.add(l);
            correct += 50f;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        for (Line l:lines) {
            l.draw(batch, parentAlpha);
        }
    }

    @Override
    public void setPosition(float x, float y){
        for (Line l:lines) {
            l.setPosition(x,y);
        }
    }

    private void sortMaxToMin(){
        List<String> name;
        List<Integer> count;
        name = new ArrayList<String>();
        count = new ArrayList<Integer>();

        for (List<String> ls:data) {
            name.add(ls.get(0));
            count.add(Integer.parseInt(ls.get(1)));
        }
        System.out.println(count);
        Integer c;
        String str;
        for (int i = 0; i < count.size()-1; i++){
            for (int j = 0; j< count.size() - 1 - i; j++){
                if (count.get(j) < count.get(j+1)){
                    c = count.get(j);
                    count.set(j, count.get(j+1));
                    count.set(j+1, c);

                    str = name.get(j);
                    name.set(j, name.get(j+1));
                    name.set(j+1, str);
                }
            }
        }

        int i = 0;
        for (List<String> ls:data) {
            ls.clear();
            ls.add(name.get(i));
            ls.add("" + count.get(i));
            i++;
        }
    }

    public List<List<String>> getData() {
        return data;
    }

    public List<Line> getLines() {
        return lines;
    }
}
