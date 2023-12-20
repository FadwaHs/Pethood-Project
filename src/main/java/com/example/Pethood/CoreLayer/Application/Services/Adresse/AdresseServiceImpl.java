package com.example.Pethood.CoreLayer.Application.Services.Adresse;

import com.example.Pethood.CoreLayer.Application.Repository.AdresseRepo;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Utilisateur.Utilisateur;
import com.example.Pethood.CoreLayer.BusinessObjects.ValueObjects.Adresse;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@Transactional
@AllArgsConstructor
public class AdresseServiceImpl implements  AdresseService{

    private AdresseRepo adresseRepo;

    @Override
    public Adresse addNewAdresse(String address, String complementAddress, String codePostal, String city, String country) {

        Adresse adresse = Adresse.builder()
                .address(address)
                .complementAddress(complementAddress)
                .codePostal(codePostal)
                .city(city)
                .country(country)
                .build();

        return adresseRepo.save(adresse);
    }
}
