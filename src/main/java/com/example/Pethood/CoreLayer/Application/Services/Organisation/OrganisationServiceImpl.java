package com.example.Pethood.CoreLayer.Application.Services.Organisation;


import com.example.Pethood.CoreLayer.Application.Repository.OrganisationRepo;
import com.example.Pethood.CoreLayer.Application.Repository.RoleRepository;
import com.example.Pethood.CoreLayer.Application.Services.Adresse.AdresseService;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Utilisateur.Organisation;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Utilisateur.Utilisateur;
import com.example.Pethood.CoreLayer.BusinessObjects.ValueObjects.Adresse;
import com.example.Pethood.CoreLayer.BusinessObjects.ValueObjects.Role;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@AllArgsConstructor
public class OrganisationServiceImpl implements  OrganisationService{

      private OrganisationRepo organisationRepo;
      private PasswordEncoder passwordEncoder;
      private AdresseService adresseService;
      private final RoleRepository roleRepository;


    @Override
    public Organisation createOrganisation( @RequestBody Map<String, Object> requestBody) {

        String email = (String) requestBody.get("email");
        String password = (String) requestBody.get("password");
        String firstname = (String) requestBody.get("firstname");
        String lastname = (String) requestBody.get("lastname");
        String phoneNumber = (String) requestBody.get("phoneNumber");

        // Reuse the existing method to create Adresse
        Adresse address = adresseService.addNewAdresse(requestBody);

        String nomStructure = (String) requestBody.get("nomStructure");
        String idOfficielStructure = (String) requestBody.get("idOfficielStructure");
        String organisationName = (String) requestBody.get("organisationName");
        String siteWeb = (String) requestBody.get("siteWeb");


        Organisation organisation = Organisation.builder()
                .SiteWeb(siteWeb)
                .OrganisationName(organisationName)
                .NomStructure(nomStructure)
                .IdOfficielStructure(idOfficielStructure)
                .email(email)
                .password(passwordEncoder.encode(password))
                .firstname(firstname)
                .lastname(lastname)
                .phoneNumber(phoneNumber)
                .address(address)
                .build();

        Role role  =  roleRepository.findByRoleName("Organisation");
        List<Role> roleList = organisation.getRoleList();
        if (roleList == null) {
            roleList = new ArrayList<>();
            organisation.setRoleList(roleList);
        }
        organisation.getRoleList().add(role);

        return organisationRepo.save(organisation);
    }
}
