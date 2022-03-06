package com.example.springrecall.Services;

import com.example.springrecall.db.MyDb;
import com.example.springrecall.models.Animal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyService {
    private final MyDb myDb;

    public MyService(MyDb myDb) {
        this.myDb = myDb;
    }

    public List<Animal> showAll() {
        return myDb.getAnimals();
    }

    public Animal showThisAnimal(Long id) {
        for (int i = 0; i<myDb.getAnimals().size(); i++){
            if(id.equals(myDb.getAnimals().get(i).getId())){
                return myDb.getAnimals().get(i);
            }
        }
        return null;
    }

    public void deleteAnimal(Long id){
        Animal animal = showThisAnimal(id);
        myDb.getAnimals().remove(animal);
    }
}
