package com.example.Pethood.CoreLayer.Application.Services.Particulier;

import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Utilisateur.Organisation;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Utilisateur.Particulier;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

public interface ParticulierService {


    public Particulier createParticulier(@RequestBody Map<String, Object> requestBody);
}
