package com.example.Pethood.CoreLayer.Application.Services.Publication.DemandeAdoption;

import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Publication.DemandeAdoption;

import java.util.Map;

public interface DemandeAdoptionService {

     DemandeAdoption createDemandeAdoption(Map<String, Object> requestBody);
}
