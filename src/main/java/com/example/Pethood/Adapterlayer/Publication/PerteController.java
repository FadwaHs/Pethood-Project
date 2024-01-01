package com.example.Pethood.Adapterlayer.Publication;
import com.example.Pethood.CoreLayer.Application.Services.Publication.Perte.PerteService;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Publication.Perte;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/perte")
@RequiredArgsConstructor
public class PerteController {

    private  final PerteService perteService;
    @PostMapping("/Add")
    public ResponseEntity<String> createPerte(@RequestBody Map<String, Object> requestBody)
    {
        Perte perte = perteService.createPerte(requestBody);
        return ResponseEntity.ok("perte created successfully with ID: " + perte.getId());
    }

    @GetMapping
    public ResponseEntity<List<Perte>> getAllPerteNotClosed(){
        return new ResponseEntity<>(perteService.getAllPerteNotClosed(), HttpStatus.OK);
    }
}
