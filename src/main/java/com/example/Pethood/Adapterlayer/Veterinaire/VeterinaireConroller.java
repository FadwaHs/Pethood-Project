package com.example.Pethood.Adapterlayer.Veterinaire;


import com.example.Pethood.CoreLayer.Application.Services.Organisation.OrganisationService;
import com.example.Pethood.CoreLayer.Application.Services.Veterinaire.VeterinaireService;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Utilisateur.Organisation;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Utilisateur.Veterinaire;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/veterinaire")
@RequiredArgsConstructor
public class VeterinaireConroller {

    private final VeterinaireService veterinaireService;
    @PostMapping("/Add")
    public ResponseEntity<String> createOrganisation(@RequestBody Map<String, Object> requestBody)
    {
        Veterinaire veterinaire = veterinaireService.createVeterinaire(requestBody);
        return ResponseEntity.ok("Veterinaire created successfully with ID: " + veterinaire.getId());
    }
}
