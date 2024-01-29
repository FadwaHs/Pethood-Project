package com.example.Pethood.CoreLayer.Application.Services.Particulier;

import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Publication.Adoption;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Publication.AlerteSoin;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Publication.Perte;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Utilisateur.Organisation;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Utilisateur.Particulier;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface ParticulierService {


    public Particulier createParticulier(@RequestBody Map<String, Object> requestBody);

    public List<AlerteSoin> getAlerteSoinForPar(Long id);

    public List<Perte> getPerteForPar(Long id);
    public List<Adoption> getAdoptionForPar(Long id);
}
