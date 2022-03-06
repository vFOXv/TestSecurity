package com.example.springrecall.models;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Animal {
    Long id;
    String kind;
    String name;
    int age;

    public Animal() {    }

    public Animal(Long id, String kind, String name, int age) {
        this.id = id;
        this.kind = kind;
        this.name = name;
        this.age = age;
    }
}
