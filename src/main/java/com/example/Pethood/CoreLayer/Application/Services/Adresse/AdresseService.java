package com.example.Pethood.CoreLayer.Application.Services.Adresse;

import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Utilisateur.Utilisateur;
import com.example.Pethood.CoreLayer.BusinessObjects.ValueObjects.Adresse;

public interface AdresseService {

    public Adresse addNewAdresse(String address, String complementAddress, String codePostal, String city, String country);
}
