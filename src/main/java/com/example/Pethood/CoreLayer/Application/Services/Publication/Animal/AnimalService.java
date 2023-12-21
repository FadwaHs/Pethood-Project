package com.example.Pethood.CoreLayer.Application.Services.Publication.Animal;

import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Publication.Animal;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

public interface AnimalService {


    public Animal createAnimal(@RequestBody Map<String, Object> requestBody);
}
