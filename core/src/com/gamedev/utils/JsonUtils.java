package com.gamedev.utils;

import com.badlogic.gdx.Gdx;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static List<List<String>> loadRecords(String filename){
        List<List<String>> result = new ArrayList<List<String>>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            try {
                JSONObject objects = (JSONObject) JSONValue.parseWithException(reader);
                JSONObject players = (JSONObject) objects.get("players");
                for (int i= 0; i<players.size(); i++){
                    JSONObject data = (JSONObject) players.get(""+i);
                    List<String> player = new ArrayList<String>();
                    player.add((String) data.get("name"));
                    player.add((String) data.get("count"));
                    result.add(player);
                    System.out.println(player);
                }
            }
            catch (Exception e){
                System.out.println("Incorrect json");
                return result;
            }
            reader.close();
        }
        catch (Exception e){
            System.out.println("Not file");
            return result;
        }
        return result;
    }

    public static void saveRecords(List<List<String>> records){
        List<List<String>> result = new ArrayList<List<String>>();
        result.addAll(records);

        JSONObject mainObject = new JSONObject();
        JSONObject idObject = new JSONObject();
        for (int i = 0; i<result.size(); i++){
            JSONObject object = new JSONObject();
            object.put("name", result.get(i).get(0));
            object.put("count", result.get(i).get(1));
            idObject.put("" + i, object);
        }
        mainObject.put("players", idObject);

        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("saves.json"));
            writer.write(mainObject.toJSONString());
            writer.close();
        }
        catch (IOException e){
            System.out.println("Error");
        }
    }
}
