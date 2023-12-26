package com.example.Pethood.CoreLayer.Application.Services.Publication.Adoption;

import com.example.Pethood.CoreLayer.Application.Repository.AdoptionRepository;
import com.example.Pethood.CoreLayer.Application.Repository.OrganisationRepo;
import com.example.Pethood.CoreLayer.Application.Repository.ParticulieRepo;
import com.example.Pethood.CoreLayer.Application.Services.Authentifier.UserDetailServiceImpl;
import com.example.Pethood.CoreLayer.Application.Services.Publication.Animal.AnimalService;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Publication.Adoption;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Publication.Animal;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Utilisateur.Organisation;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Utilisateur.Particulier;
import com.example.Pethood.CoreLayer.BusinessObjects.Enum.AdoptionStatus;
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
public class AdoptionServiceImpl implements AdoptionService {

    private AdoptionRepository adoptionRepository;
    private OrganisationRepo organisationRepo;
    private ParticulieRepo particulierRepo;
    private AnimalService animalService;
    private UserDetailServiceImpl userDetailServiceImpl;

    @Override
    public Adoption createAdoption(Map<String, Object> requestBody) {

        Animal animal = animalService.createAnimal(requestBody);

        String titre = (String) requestBody.get("titre");
        String description = (String) requestBody.get("Description");
        String uRLImage = (String) requestBody.get("uRLImage");
        String infos = (String) requestBody.get("infos");
        Double frais_adoption = (Double) requestBody.get("frais_Adoption");

        String email_user = (String) requestBody.get("email");
        String role = (String) requestBody.get("scope");

        Adoption adoption = Adoption.builder()
                .Titre(titre)
                .Description(description)
                .URLImage(uRLImage)
                .Infos(infos)
                .Frais_Adoption(frais_adoption)
                .date_publication(new Date())
                .animal(animal)
                .build();

        if(role.equals("Organisation"))
        {
            Organisation organisation = organisationRepo.findByEmail(email_user);
            adoption.setOrganisation(organisation);

        }else if(role.equals("Particulier"))
        {
            Particulier particulier = particulierRepo.findByEmail(email_user);
            adoption.setParticulier(particulier);
        }
          return adoptionRepository.save(adoption);
    }


    @Override
    public Adoption getAdoptionById(Long id) {

        return adoptionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Adoption> getAllAdoptionNotAdopted() {

        // Retrieve authentication details from the SecurityContextHolder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        // Retrieve additional user details (roles) using the UserDetailsService
        UserDetails userDetails = userDetailServiceImpl.loadUserByUsername(userEmail);
        String role = userDetails.getAuthorities().stream()
                .map(a -> a.getAuthority())
                .findFirst()
                .orElse(null);

        // Determine the user ID based on the user's role
        Long userId;

        if ("Organisation".equals(role)) {
            Organisation organisation = organisationRepo.findByEmail(userEmail);
            userId = organisation.getId();
        } else if ("Particulier".equals(role)) {
            Particulier particulier = particulierRepo.findByEmail(userEmail);
            userId = particulier.getId();
        } else {
            userId = null;
        }

        List<Adoption> allAdoptions = adoptionRepository.findAll();

        // Use Java Stream API to filter and collect adoption posts
        // Filter: Include only adoptions with a status different from "ADOPTE"
        // Filter: Exclude adoptions posted by the current user based on their role and ID
        return allAdoptions.stream()
                .filter(adoption -> !adoption.getStatus().equals(AdoptionStatus.ADOPTE))
                .filter(adoption -> !isPostedByCurrentUser(adoption, role, userId))
                .collect(Collectors.toList());
    }


    // Helper method to check if the adoption post is posted by the current user
    private boolean isPostedByCurrentUser(Adoption adoption, String role, Long userId) {
        if ("Organisation".equals(role)) {
            return adoption.getOrganisation() != null && adoption.getOrganisation().getId().equals(userId);
        } else if ("Particulier".equals(role)) {
            return adoption.getParticulier() != null && adoption.getParticulier().getId().equals(userId);
        } else {
            return false;
        }
    }




}
