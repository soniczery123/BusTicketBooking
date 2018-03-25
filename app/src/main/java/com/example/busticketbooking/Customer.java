package com.example.busticketbooking;

/**
 * Created by MSI-GL62 on 25/3/2561.
 */

public class Customer {
    private String name;
    private String surname;
    private String gender;
    private int age;
    private String seat;

    public Customer(String name, String surname, String gender, int age, String seat) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.age = age;
        this.seat = seat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }
}
