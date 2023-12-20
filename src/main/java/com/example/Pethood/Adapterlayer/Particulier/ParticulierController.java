package com.example.Pethood.Adapterlayer.Particulier;


import com.example.Pethood.CoreLayer.Application.Services.Particulier.ParticulierService;
import com.example.Pethood.CoreLayer.Application.Services.Veterinaire.VeterinaireService;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Utilisateur.Particulier;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Utilisateur.Veterinaire;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/particulier")
@RequiredArgsConstructor
public class ParticulierController {


    private final ParticulierService particulierService;

    @PostMapping("/Add")
    public ResponseEntity<String> createParticulier(@RequestBody Map<String, Object> requestBody)
    {
        Particulier particulier = particulierService.createParticulier(requestBody);
        return ResponseEntity.ok("particulier created successfully with ID: " + particulier.getId());
    }
}
