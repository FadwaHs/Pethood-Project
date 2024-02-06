package com.example.Pethood.Adapterlayer.Publication;


import com.example.Pethood.CoreLayer.Application.Services.Publication.Adoption.AdoptionService;
import com.example.Pethood.CoreLayer.Application.Services.Veterinaire.VeterinaireService;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Publication.Adoption;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Utilisateur.Veterinaire;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/adoption")
@RequiredArgsConstructor
public class AdoptionController {

    private final AdoptionService adoptionService;
    @PostMapping("/Add")
    public ResponseEntity<String> createAdoption(@RequestBody Map<String, Object> requestBody)
    {
        Adoption adoption = adoptionService.createAdoption(requestBody);
        return ResponseEntity.ok("Adoption created successfully with ID: " + adoption.getId());
    }

    @GetMapping("{id}")
    public ResponseEntity<Adoption> getAdoptionById(@PathVariable Long id){
        return new ResponseEntity<>(adoptionService.getAdoptionById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Adoption>> getAllAdoptionNotAdopted(){
        return new ResponseEntity<>(adoptionService.getAllAdoptionNotAdopted(), HttpStatus.OK);
    }


    // Recherche Part /////////////////////////////
    @GetMapping("/searchByAnimal")
    public ResponseEntity<List<Adoption>> searchByAnimalAttributes(@RequestParam String searchData) {
        return new ResponseEntity<>(adoptionService.findByDataContainingAnimal(searchData), HttpStatus.OK);

    }

}
