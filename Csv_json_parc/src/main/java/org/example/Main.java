package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.ColumnPositionMappingStrategy;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Маппинг колонок
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        // Имя CSV-файла
        String fileName = "data.csv";

        // Получаем список сотрудников
        List<Employee> list = parseCSV(columnMapping, fileName);

        // Преобразуем список в JSON
        String json = listToJson(list);

        // Записываем JSON в файл
        writeString(json, "data.json");
    }

    public static List<Employee> parseCSV(String[] columnMapping, String fileName) {
        try (FileReader fileReader = new FileReader(fileName)) {
            // Настройка стратегии маппинга
            ColumnPositionMappingStrategy<Employee> strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(Employee.class);
            strategy.setColumnMapping(columnMapping);

            // Создание CsvToBean
            CsvToBean<Employee> csvToBean = new CsvToBeanBuilder<Employee>(fileReader)
                    .withMappingStrategy(strategy)
                    .withSkipLines(1) // Пропустить заголовок
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            // Парсинг CSV и возврат списка сотрудников
            return csvToBean.parse();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String listToJson(List<Employee> list) {
        // Создаем объект Gson с красивым форматированием
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // Определяем тип списка для корректного преобразования в JSON
        Type listType = new TypeToken<List<Employee>>() {}.getType();

        // Преобразуем список в JSON
        return gson.toJson(list, listType);
    }

    public static void writeString(String json, String fileName) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            // Записываем JSON в файл
            fileWriter.write(json);
            System.out.println("JSON успешно записан в файл: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}