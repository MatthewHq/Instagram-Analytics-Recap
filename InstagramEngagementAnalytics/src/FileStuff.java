
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Matthew
 */
public class FileStuff {

    public static void dataToJson(HashMap<String, Object> json) throws IOException {
        Gson gson = new Gson();
        File x = new File("JSON.txt");
        x.delete();
        x.createNewFile();
//        System.out.println(gson.toJson(MessageListener.users));
//        gson.toJson(MessageListener.users, new FileWriter(x.getName()));

        String str = gson.toJson(json);

        System.out.println("saving" + gson.toJson(json));
        BufferedWriter writer = new BufferedWriter(new FileWriter(x));
        writer.write(str);
        writer.close();
    }

    public static File dataToJsonCustomFile(HashMap<String, Object> json, String text) throws IOException {
        Gson gson = new Gson();
        File x = new File(text + ".JSON");
        System.out.println(x.getAbsoluteFile() + " path");
        try {
            x.delete();
        } catch (Exception e) {
        }
        x.createNewFile();
//        System.out.println(gson.toJson(MessageListener.users));
//        gson.toJson(MessageListener.users, new FileWriter(x.getName()));

        String str = gson.toJson(json);

        System.out.println("msgJSON" + gson.toJson(json));
        BufferedWriter writer = new BufferedWriter(new FileWriter(x));
        writer.write(str);
        writer.close();

        return x;
    }

    public static HashMap<String, Object> JSONtoData() throws IOException {
        Gson gson = new Gson();
        File x = new File("JSON.txt");

        if (!x.exists()) {
            x.createNewFile();
        }

        //Deep clone
        BufferedReader reader;
        reader = new BufferedReader(new FileReader("JSON.txt"));
        String line = reader.readLine();
//        System.out.println(gson.toJson(MessageListener.users));

        Type type = new TypeToken<HashMap<String, Object>>() {
        }.getType();
        HashMap<String, Object> clonedMap = gson.fromJson(line, type);
        return clonedMap;

    }

}
