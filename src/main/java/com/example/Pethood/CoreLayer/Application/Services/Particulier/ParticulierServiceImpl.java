package com.example.Pethood.CoreLayer.Application.Services.Particulier;

import com.example.Pethood.CoreLayer.Application.Repository.ParticulieRepo;
import com.example.Pethood.CoreLayer.Application.Repository.RoleRepository;
import com.example.Pethood.CoreLayer.Application.Services.Adresse.AdresseService;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Utilisateur.Organisation;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Utilisateur.Particulier;
import com.example.Pethood.CoreLayer.BusinessObjects.ValueObjects.Adresse;
import com.example.Pethood.CoreLayer.BusinessObjects.ValueObjects.Role;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


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

        Adresse address = adresseService.addNewAdresse(
                (String) requestBody.get("address"),
                (String) requestBody.get("complementAddress"),
                (String) requestBody.get("codePostal"),
                (String) requestBody.get("city"),
                (String) requestBody.get("country")
        );

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
}
