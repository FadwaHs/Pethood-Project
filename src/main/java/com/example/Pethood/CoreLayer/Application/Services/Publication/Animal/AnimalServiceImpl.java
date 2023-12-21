package com.example.Pethood.CoreLayer.Application.Services.Publication.Animal;

import com.example.Pethood.CoreLayer.Application.Repository.AnimalRepository;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Publication.Animal;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
@Transactional
@AllArgsConstructor
public class AnimalServiceImpl implements  AnimalService{

    private AnimalRepository animalRepository;

    @Override
    public Animal createAnimal(Map<String, Object> requestBody) {

        String type = (String) requestBody.get("type");
        String name = (String) requestBody.get("name");
        String race = (String) requestBody.get("race");
        String sexe = (String) requestBody.get("sexe");
        Double age = (Double) requestBody.get("age");
        String sante = (String) requestBody.get("sante");
        boolean isVaccinated = (boolean) requestBody.get("isVaccinated");
        String lieu = (String) requestBody.get("lieu");

        Animal animal = Animal.builder()
                .Type(type)
                .Name(name)
                .Sexe(sexe)
                .Sante(sante)
                .Age(age)
                .Race(race)
                .isVaccinated(isVaccinated)
                .Lieu(lieu)
                .build();

        return animalRepository.save(animal);
    }
}
