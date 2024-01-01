package com.example.Pethood.Adapterlayer.Publication;


import com.example.Pethood.CoreLayer.Application.Services.Publication.AlertSoin.AlertSoinService;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Publication.Adoption;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Publication.AlerteSoin;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/alertsoin")
@RequiredArgsConstructor
public class AlertSoinController {

    private  final AlertSoinService alertSoinService;

    @PostMapping("/Add")
    public ResponseEntity<String> createAlertSoin(@RequestBody Map<String, Object> requestBody)
    {
        AlerteSoin alerteSoin = alertSoinService.createAlertSoin(requestBody);
        return ResponseEntity.ok("alerteSoin created successfully with ID: " + alerteSoin.getId());
    }

    @GetMapping
    public ResponseEntity<List<AlerteSoin>> getAllAlertSoinNotResolved(){
        return new ResponseEntity<>(alertSoinService.getAllAlertSoinNotResolved(), HttpStatus.OK);
    }
}
