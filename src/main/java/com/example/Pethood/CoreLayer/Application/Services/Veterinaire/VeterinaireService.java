package com.example.Pethood.CoreLayer.Application.Services.Veterinaire;

import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Utilisateur.Veterinaire;
import com.example.Pethood.CoreLayer.BusinessObjects.ValueObjects.Adresse;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Map;

public interface VeterinaireService {

    public Veterinaire createVeterinaire(@RequestBody Map<String, Object> requestBody);
}
