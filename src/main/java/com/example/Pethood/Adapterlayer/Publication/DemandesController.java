package com.example.Pethood.Adapterlayer.Publication;


import com.example.Pethood.CoreLayer.Application.Services.Publication.Adoption.AdoptionService;
import com.example.Pethood.CoreLayer.Application.Services.Publication.DemandeAdoption.DemandeAdoptionService;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Publication.Adoption;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Publication.DemandeAdoption;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/demandes")
@RequiredArgsConstructor
public class DemandesController {


    private final DemandeAdoptionService demandeAdoptionService;
    @PostMapping("/Add")
    public ResponseEntity<String> createDemandeAdoption(@RequestBody Map<String, Object> requestBody)
    {
        DemandeAdoption demandeAdoption = demandeAdoptionService.createDemandeAdoption(requestBody);
        return ResponseEntity.ok(" Demande Adoption created successfully with ID: " + demandeAdoption.getId());
    }

}
