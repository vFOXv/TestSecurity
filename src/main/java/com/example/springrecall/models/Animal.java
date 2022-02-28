package com.example.springrecall.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConstructorBinding;


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
