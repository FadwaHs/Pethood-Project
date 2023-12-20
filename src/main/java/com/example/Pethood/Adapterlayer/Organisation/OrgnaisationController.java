package com.example.Pethood.Adapterlayer.Organisation;


import com.example.Pethood.CoreLayer.Application.Services.Adresse.AdresseService;
import com.example.Pethood.CoreLayer.Application.Services.Organisation.OrganisationService;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Utilisateur.Organisation;
import com.example.Pethood.CoreLayer.BusinessObjects.ValueObjects.Adresse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/api/organisation")
@RequiredArgsConstructor
public class OrgnaisationController {

    private final OrganisationService organisationService;

    @PostMapping("/Add")
    public ResponseEntity<String> createOrganisation(@RequestBody Map<String, Object> requestBody)
    {
        Organisation organisation = organisationService.createOrganisation(requestBody);
        return ResponseEntity.ok("Organisation created successfully with ID: " + organisation.getId());
    }
}
