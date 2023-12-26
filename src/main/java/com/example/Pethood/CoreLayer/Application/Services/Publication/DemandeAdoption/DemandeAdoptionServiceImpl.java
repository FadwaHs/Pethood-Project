package com.example.Pethood.CoreLayer.Application.Services.Publication.DemandeAdoption;
import com.example.Pethood.CoreLayer.Application.Repository.DemandeAdoptionRepository;
import com.example.Pethood.CoreLayer.Application.Repository.ParticulieRepo;
import com.example.Pethood.CoreLayer.Application.Services.Publication.Adoption.AdoptionService;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Publication.Adoption;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Publication.DemandeAdoption;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Utilisateur.Particulier;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
@Transactional
@AllArgsConstructor
public class DemandeAdoptionServiceImpl implements DemandeAdoptionService{

    private ParticulieRepo particulieRepo;
    private AdoptionService adoptionService;
    private DemandeAdoptionRepository demandeAdoptionRepository;

    @Override
    public DemandeAdoption createDemandeAdoption(Map<String, Object> requestBody) {

        String description = (String) requestBody.get("description");
        String email_particulier = (String) requestBody.get("email");
        Long adoptionId = ((Number)requestBody.get("adoptionId")).longValue();

        // Retrieve the particulier based on the provided Email
        Particulier particulier = particulieRepo.findByEmail(email_particulier);

        // Retrieve the adoption based on the provided adoptionId
        Adoption adoption = adoptionService.getAdoptionById(adoptionId);

        DemandeAdoption demandeAdoption = DemandeAdoption.builder()
                .date_Demande(new Date())
                .Description(description)
                .particulier(particulier)
                .adoption(adoption)
                .build();

        return demandeAdoptionRepository.save(demandeAdoption);
    }
}
