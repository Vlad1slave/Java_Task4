package org.example;

import java.io.BufferedReader;
import java.util.List;
import java.io.FileReader;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Main {
    public static void main(String[] args) {

        String json = readString("new_data.json");
        List<Employee> list = jsonToList(json);
        for (Employee employee : list) {
            System.out.println(employee);

        }

    }

    public static String readString(String fileName) {
        StringBuilder json = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                json.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json.toString();
    }

    public static List<Employee> jsonToList(String json) {
        List<Employee> list = null;
        try {
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(json);
            Gson gson = new GsonBuilder().create();
            list = gson.fromJson(jsonArray.toJSONString(), new TypeToken<List<Employee>>() {}.getType());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }
}