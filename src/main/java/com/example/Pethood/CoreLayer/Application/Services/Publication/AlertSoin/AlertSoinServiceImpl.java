package com.example.Pethood.CoreLayer.Application.Services.Publication.AlertSoin;

import com.example.Pethood.CoreLayer.Application.Repository.AlerteSoinRepository;
import com.example.Pethood.CoreLayer.Application.Repository.ParticulieRepo;
import com.example.Pethood.CoreLayer.Application.Services.Authentifier.UserDetailServiceImpl;
import com.example.Pethood.CoreLayer.Application.Services.Publication.Animal.AnimalService;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Publication.Adoption;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Publication.AlerteSoin;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Publication.Animal;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Utilisateur.Particulier;
import com.example.Pethood.CoreLayer.BusinessObjects.Enum.AdoptionStatus;
import com.example.Pethood.CoreLayer.BusinessObjects.Enum.AlerteSoinStatus;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class AlertSoinServiceImpl  implements  AlertSoinService{

    private ParticulieRepo particulierRepo;
    private AnimalService animalService;
    private AlerteSoinRepository alerteSoinRepository;
    private UserDetailServiceImpl userDetailServiceImpl;

    @Override
    public AlerteSoin createAlertSoin(Map<String, Object> requestBody) {

        Animal animal = animalService.createAnimal(requestBody);

        String titre = (String) requestBody.get("titre");
        String description = (String) requestBody.get("Description");
        String uRLImage = (String) requestBody.get("uRLImage");

        String descriptionMaladie = (String) requestBody.get("descriptionMaladie");
        Double CoûtTraitement = (Double) requestBody.get("CoûtTraitement");

        String particulier_user = (String) requestBody.get("email");
        Particulier particulier = particulierRepo.findByEmail(particulier_user);

        AlerteSoin alerteSoin = AlerteSoin.builder()
                .Titre(titre)
                .Description(description)
                .URLImage(uRLImage)
                .DescriptionMaladie(descriptionMaladie)
                .CoûtTraitement(CoûtTraitement)
                .particulier(particulier)
                .date_publication(new Date())
                .animal(animal)
                .build();

           return alerteSoinRepository.save(alerteSoin);
    }

    @Override
    public List<AlerteSoin> getAllAlertSoinNotResolved() {

        // Retrieve authentication details from the SecurityContextHolder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        // Retrieve additional user details (roles) using the UserDetailsService
        UserDetails userDetails = userDetailServiceImpl.loadUserByUsername(userEmail);
        String role = userDetails.getAuthorities().stream()
                .map(a -> a.getAuthority())
                .findFirst()
                .orElse(null);

        Long userId;
        if ("Particulier".equals(role)) {
            Particulier particulier = particulierRepo.findByEmail(userEmail);
            userId = particulier.getId();
        }
        else
        { userId = null; }

        List<AlerteSoin> allalertsoin = alerteSoinRepository.findAll();

        // Use Java Stream API to filter and collect alertSoin  posts
        // Filter: Include only alertSoin with a status different from "CAS_RESOLU"
        // Filter: Exclude alertSoin posted by the current partuculier user based on role and ID
        return allalertsoin.stream()
                .filter(alertsoin -> !alertsoin.getStatus().equals(AlerteSoinStatus.CAS_RESOLU))
                .filter(alertsoin -> !isPostedByCurrentPart(alertsoin,role, userId))
                .collect(Collectors.toList());
    }


    // Helper method to check if the alertSoin post is posted by the current user 'Particulier'
    private boolean isPostedByCurrentPart(AlerteSoin alerteSoin, String role, Long userId) {
         if ("Particulier".equals(role)) {
            return alerteSoin.getParticulier() != null && alerteSoin.getParticulier().getId().equals(userId);
        } else {
            return false;
        }
    }


}
