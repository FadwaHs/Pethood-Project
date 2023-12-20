package com.example.Pethood.CoreLayer.Application.Services.Organisation;

import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Utilisateur.Organisation;
import com.example.Pethood.CoreLayer.BusinessObjects.ValueObjects.Adresse;
import com.example.Pethood.CoreLayer.BusinessObjects.ValueObjects.Role;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

public interface OrganisationService {

    public Organisation createOrganisation(@RequestBody Map<String, Object> requestBody);
}
