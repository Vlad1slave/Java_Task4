package org.example;

public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private String country;
    private int age;

    // Конструктор по умолчанию необходим для Gson
    public Employee() {}

    // Конструктор для удобства
    public Employee(int id, String firstName, String lastName, String country, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", country='" + country + '\'' +
                ", age=" + age +
                '}';
    }
}