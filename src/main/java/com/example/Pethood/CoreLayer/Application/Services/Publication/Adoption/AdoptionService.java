package com.example.Pethood.CoreLayer.Application.Services.Publication.Adoption;

import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Publication.Adoption;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface AdoptionService {

    public Adoption createAdoption(@RequestBody Map<String, Object> requestBody);
    public Adoption getAdoptionById(Long id);

    public List<Adoption> getAllAdoption();
}
