package com.example.Pethood.Adapterlayer.Particulier;


import com.example.Pethood.CoreLayer.Application.Services.Particulier.ParticulierService;
import com.example.Pethood.CoreLayer.Application.Services.Veterinaire.VeterinaireService;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Publication.Adoption;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Publication.AlerteSoin;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Publication.Perte;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Utilisateur.Particulier;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Utilisateur.Veterinaire;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("Alsoin/{id}")
    public ResponseEntity<List<AlerteSoin>> getAlerteSoinForPar(@PathVariable Long id ){

        return new ResponseEntity<>(particulierService.getAlerteSoinForPar(id), HttpStatus.OK);
    }


    @GetMapping("perte/{id}")
    public ResponseEntity<List<Perte>> getPerteForPar(@PathVariable Long id ){

        return new ResponseEntity<>(particulierService.getPerteForPar(id), HttpStatus.OK);
    }

    @GetMapping("adoption/{id}")
    public ResponseEntity<List<Adoption>> getAdoptionForPar(@PathVariable Long id ){

        return new ResponseEntity<>(particulierService.getAdoptionForPar(id), HttpStatus.OK);
    }
}
