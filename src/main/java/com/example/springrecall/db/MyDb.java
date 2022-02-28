package com.example.springrecall.db;

import com.example.springrecall.models.Animal;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
public class MyDb {
List<Animal> animals = new ArrayList<>();
    public MyDb(){
        animals.add(new Animal(1L,"Cat", "Mursik",3));
        animals.add(new Animal(2L,"Dog", "Reks",5));
        animals.add(new Animal(3L,"Fish", "Fishhh",1));
    }
}
