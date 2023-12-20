package com.example.Pethood.Adapterlayer.Adresse;


import com.example.Pethood.CoreLayer.Application.Services.Adresse.AdresseService;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Utilisateur.Utilisateur;
import com.example.Pethood.CoreLayer.BusinessObjects.ValueObjects.Adresse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AdresseService addressService;

    @PostMapping("/create")
    public ResponseEntity<String> createAdresse(@RequestBody Map<String, Object> requestBody) {

        String address = (String) requestBody.get("address");
        String complementAddress = (String) requestBody.get("complementAddress");
        String codePostal = (String) requestBody.get("codePostal");
        String city = (String) requestBody.get("city");
        String country = (String) requestBody.get("country");

        Adresse adresse = addressService.addNewAdresse(
                address,
                complementAddress,
                codePostal,
                city,
                country
        );

        return ResponseEntity.ok("Adresse created successfully with ID: " + adresse.getId());
    }



}
