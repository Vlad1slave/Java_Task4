package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.ColumnPositionMappingStrategy;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Получаем список сотрудников из XML
        List<Employee> list = parseXML("data.xml");

        // Преобразуем список в JSON
        String json = listToJson(list);

        // Записываем JSON в файл
        writeString(json, "data2.json");
    }

    public static List<Employee> parseXML(String fileName) {
        List<Employee> employeeList = new ArrayList<>();

        try {
            // Создаем фабрику и парсер для XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Парсим XML-файл
            Document document = builder.parse(new File(fileName));

            // Получаем корневой элемент
            Element root = document.getDocumentElement();

            // Получаем список всех элементов <employee>
            NodeList nodeList = root.getChildNodes();

            // Проходим по всем элементам <employee>
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                // Проверяем, что это элемент (игнорируем текстовые узлы)
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    // Извлекаем данные из элемента
                    int id = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
                    String firstName = element.getElementsByTagName("firstName").item(0).getTextContent();
                    String lastName = element.getElementsByTagName("lastName").item(0).getTextContent();
                    String country = element.getElementsByTagName("country").item(0).getTextContent();
                    int age = Integer.parseInt(element.getElementsByTagName("age").item(0).getTextContent());

                    // Создаем объект Employee и добавляем его в список
                    employeeList.add(new Employee(id, firstName, lastName, country, age));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return employeeList;
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