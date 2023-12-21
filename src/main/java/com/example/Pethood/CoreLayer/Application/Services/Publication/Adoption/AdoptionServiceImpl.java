package com.example.Pethood.CoreLayer.Application.Services.Publication.Adoption;

import com.example.Pethood.CoreLayer.Application.Repository.AdoptionRepository;
import com.example.Pethood.CoreLayer.Application.Repository.OrganisationRepo;
import com.example.Pethood.CoreLayer.Application.Repository.ParticulieRepo;
import com.example.Pethood.CoreLayer.Application.Services.Publication.Animal.AnimalService;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Publication.Adoption;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Publication.Animal;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Utilisateur.Organisation;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Utilisateur.Particulier;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
@Transactional
@AllArgsConstructor
public class AdoptionServiceImpl implements AdoptionService {

    private AdoptionRepository adoptionRepository;
    private OrganisationRepo organisationRepo;
    private ParticulieRepo particulierRepo;
    private AnimalService animalService;

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
    public List<Adoption> getAllAdoption() {
        return  adoptionRepository.findAll();
    }
}
