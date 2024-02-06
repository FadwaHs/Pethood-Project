package com.example.Pethood.CoreLayer.Application.Services.Publication.Perte;

import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Publication.Perte;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import java.util.Map;

public interface PerteService {
    Perte createPerte(@RequestBody Map<String, Object> requestBody);
    List<Perte> getAllPerteNotClosed();

    List<Perte> findByDataContainingAnimal(String searchData);
}
