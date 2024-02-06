package com.example.Pethood.CoreLayer.Application.Services.Publication.Perte;

import com.example.Pethood.CoreLayer.Application.Repository.ParticulieRepo;
import com.example.Pethood.CoreLayer.Application.Repository.PerteRepository;
import com.example.Pethood.CoreLayer.Application.Services.Authentifier.UserDetailServiceImpl;
import com.example.Pethood.CoreLayer.Application.Services.Publication.Animal.AnimalService;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Publication.AlerteSoin;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Publication.Animal;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Publication.Perte;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Utilisateur.Particulier;
import com.example.Pethood.CoreLayer.BusinessObjects.Enum.PerteStatus;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@Transactional
@AllArgsConstructor
public class PerteServiceImpl  implements  PerteService{

    private PerteRepository perteRepository;
    private AnimalService animalService;
    private ParticulieRepo particulierRepo;
    private UserDetailServiceImpl userDetailServiceImpl;

    @Override
    public Perte createPerte(Map<String, Object> requestBody) {

        Animal animal = animalService.createAnimal(requestBody);

        String titre = (String) requestBody.get("titre");
        String description = (String) requestBody.get("Description");
        String uRLImage = (String) requestBody.get("uRLImage");

        String DateDisparition = (String) requestBody.get("DateDisparition");
        String LieuDisparition = (String) requestBody.get("lieuDisparition");
        String ProprietaireAnimal = (String) requestBody.get("proprietaireAnimal");
        String ContactSOS = (String) requestBody.get("ContactSOS");

        Date DateDispa = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
            DateDispa = dateFormat.parse(DateDisparition);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String particulier_user = (String) requestBody.get("email");
        Particulier particulier = particulierRepo.findByEmail(particulier_user);

        Perte perte = Perte.builder()
                .Titre(titre)
                .Description(description)
                .URLImage(uRLImage)
                .DateDisparition(DateDispa)
                .LieuDisparition(LieuDisparition)
                .ProprietaireAnimal(ProprietaireAnimal)
                .ContactSOS(ContactSOS)
                .particulier(particulier)
                .animal(animal)
                .date_publication(new Date())
                .build();

        return perteRepository.save(perte);
    }

    @Override
    public List<Perte> getAllPerteNotClosed() {

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

        List<Perte> allperteList = perteRepository.findAll();

        // Filter: Include only perte with a status different from "CLOTURE"
        // Filter: Exclude perte posted by the current partuculier user based on role and ID
        return allperteList.stream()
                .filter(perte -> !perte.getStatus().equals(PerteStatus.CLOTURE))
                .filter(perte -> !isPostedByCurrentPart(perte,role, userId))
                .collect(Collectors.toList());

    }


    // Helper method to check if the perte post is posted by the current user 'Particulier'
    private boolean isPostedByCurrentPart(Perte perte, String role, Long userId) {
        if ("Particulier".equals(role)) {
            return perte.getParticulier() != null && perte.getParticulier().getId().equals(userId);
        } else {
            return false;
        }
    }


    // Recherche ////////////////////////////////////////////////////////////
    @Override
    public List<Perte> findByDataContainingAnimal(String searchData) {

        return perteRepository.findByDataContaining(searchData);
    }
}
















