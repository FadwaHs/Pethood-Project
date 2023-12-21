package com.example.Pethood.CoreLayer.Application.Services.Adresse;

import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Utilisateur.Utilisateur;
import com.example.Pethood.CoreLayer.BusinessObjects.ValueObjects.Adresse;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

public interface AdresseService {

    public Adresse addNewAdresse(@RequestBody Map<String, Object> requestBody);
}
