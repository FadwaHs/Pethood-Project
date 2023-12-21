package com.example.Pethood.Adapterlayer.Multimedia;


import com.example.Pethood.CoreLayer.Application.Services.MultiMedia.EvenementService;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Multimedia.Evenement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/evenement")
@RequiredArgsConstructor
public class EvenementController {

    private final EvenementService evenementService;
    @PostMapping("/Add")
    public ResponseEntity<String> createAdoption(@RequestBody Map<String, Object> requestBody)
    {
        Evenement evenement = evenementService.createEvenement(requestBody);
        return ResponseEntity.ok("Evenement created successfully with ID: " + evenement.getId());
    }

}
