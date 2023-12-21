package com.example.Pethood.CoreLayer.Application.Services.Adresse;

import com.example.Pethood.CoreLayer.Application.Repository.AdresseRepo;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Utilisateur.Utilisateur;
import com.example.Pethood.CoreLayer.BusinessObjects.ValueObjects.Adresse;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;


@Service
@Transactional
@AllArgsConstructor
public class AdresseServiceImpl implements  AdresseService{

    private AdresseRepo adresseRepo;

    @Override
    public Adresse addNewAdresse(@RequestBody Map<String, Object> requestBody) {

        String address = (String) requestBody.get("address");
        String complementAddress = (String) requestBody.get("complementAddress");
        String codePostal = (String) requestBody.get("codePostal");
        String city = (String) requestBody.get("city");
        String country = (String) requestBody.get("country");

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
