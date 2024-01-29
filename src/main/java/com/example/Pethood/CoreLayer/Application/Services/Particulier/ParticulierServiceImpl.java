package com.example.Pethood.CoreLayer.Application.Services.Particulier;

import com.example.Pethood.CoreLayer.Application.Repository.ParticulieRepo;
import com.example.Pethood.CoreLayer.Application.Repository.RoleRepository;
import com.example.Pethood.CoreLayer.Application.Services.Adresse.AdresseService;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Publication.Adoption;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Publication.AlerteSoin;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Publication.Perte;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Utilisateur.Organisation;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Utilisateur.Particulier;
import com.example.Pethood.CoreLayer.BusinessObjects.ValueObjects.Adresse;
import com.example.Pethood.CoreLayer.BusinessObjects.ValueObjects.Role;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@Transactional
@AllArgsConstructor
public class ParticulierServiceImpl  implements  ParticulierService{


    private AdresseService adresseService;
    private PasswordEncoder passwordEncoder;
    private ParticulieRepo particulieRepo;
    private  final RoleRepository roleRepository;
    @Override
    public Particulier createParticulier(Map<String, Object> requestBody) {

        String email = (String) requestBody.get("email");
        String password = (String) requestBody.get("password");
        String firstname = (String) requestBody.get("firstname");
        String lastname = (String) requestBody.get("lastname");
        String phoneNumber = (String) requestBody.get("phoneNumber");

        String dateNaissance = (String) requestBody.get("dateNaissance");
        String sexe = (String) requestBody.get("sexe");

        Adresse address = adresseService.addNewAdresse(requestBody);

        Particulier particulier = Particulier.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .firstname(firstname)
                .lastname(lastname)
                .phoneNumber(phoneNumber)
                .address(address)
                .Sexe(sexe)
                .DateNaissance(dateNaissance)
                .build();

        Role role  =  roleRepository.findByRoleName("Particulier");
        List<Role> roleList = particulier.getRoleList();
        if (roleList == null) {
            roleList = new ArrayList<>();
            particulier.setRoleList(roleList);
        }
        particulier.getRoleList().add(role);

        return particulieRepo.save(particulier);
    }

    @Override
    public List<AlerteSoin> getAlerteSoinForPar(Long id) {

        Optional<Particulier> particulierOptional = particulieRepo.findById(id);

        if (particulierOptional.isPresent()  ){

            Particulier particulier = particulierOptional.get();
            particulier.initializeCollections();
            return particulier.getAlerteSoinList();
        }
        return Collections.emptyList();
    }

    @Override
    public List<Perte> getPerteForPar(Long id) {

        Optional<Particulier> particulierOptional = particulieRepo.findById(id);

        if (particulierOptional.isPresent()  ){

            Particulier particulier = particulierOptional.get();
            particulier.initializeCollections();
            return particulier.getPerteList();
        }
        return Collections.emptyList();
    }

    @Override
    public List<Adoption> getAdoptionForPar(Long id) {

        Optional<Particulier> particulierOptional = particulieRepo.findById(id);

        if (particulierOptional.isPresent()  ){

            Particulier particulier = particulierOptional.get();
            particulier.initializeCollections();
            return particulier.getAdoptionList();
        }
        return Collections.emptyList();
    }


}
