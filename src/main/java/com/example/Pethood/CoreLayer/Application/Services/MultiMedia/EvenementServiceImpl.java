package com.example.Pethood.CoreLayer.Application.Services.MultiMedia;


import com.example.Pethood.CoreLayer.Application.Repository.EevenementRepository;
import com.example.Pethood.CoreLayer.Application.Repository.OrganisationRepo;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Multimedia.Evenement;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Utilisateur.Organisation;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Service
@Transactional
@AllArgsConstructor
public class EvenementServiceImpl implements  EvenementService{

           private EevenementRepository evenementRepository;
           private OrganisationRepo organisationRepo;


    @Override
    public Evenement createEvenement(Map<String, Object> requestBody) {

        String titre = (String) requestBody.get("titre");
        String description = (String) requestBody.get("description");
        String image = (String) requestBody.get("image");
        String lieuEvenement = (String) requestBody.get("lieuEvenement");
        String typeEvenement = (String) requestBody.get("typeEvenement");
        String organisateur = (String) requestBody.get("organisateur");
        String intervenants = (String) requestBody.get("intervenants");
        String email = (String) requestBody.get("email"); // Assuming this is the email of the organisation

        String dateEventStr = (String) requestBody.get("dateEvent");
        Date dateEvent = null;

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
            dateEvent = dateFormat.parse(dateEventStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Evenement evenement = Evenement.builder()
                .Titre(titre)
                .Description(description)
                .Image(image)
                .LieuEvenement(lieuEvenement)
                .TypeEvenement(typeEvenement)
                .Organisateur(organisateur)
                .Intervenants(intervenants)
                .date_publication(new Date())
                .date_Event(dateEvent)
                .build();

        Organisation organisation = organisationRepo.findByEmail(email);
        evenement.setOrganisation(organisation);

        return evenementRepository.save(evenement);
    }





}
