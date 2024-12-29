package org.example;

public class Employee {
    private int id;          // Уникальный идентификатор сотрудника
    private String firstName; // Имя сотрудника
    private String lastName;  // Фамилия сотрудника
    private String country;   // Страна сотрудника
    private int age;          // Возраст сотрудника

    // Конструктор по умолчанию (необходим для некоторых библиотек, например, Gson)
    public Employee() {
    }

    // Конструктор с параметрами
    public Employee(int id, String firstName, String lastName, String country, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.age = age;
    }

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // Переопределение метода toString() для удобного вывода информации о сотруднике
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