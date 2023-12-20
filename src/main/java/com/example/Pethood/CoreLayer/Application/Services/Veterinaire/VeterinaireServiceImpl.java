package com.example.Pethood.CoreLayer.Application.Services.Veterinaire;


import com.example.Pethood.CoreLayer.Application.Repository.RoleRepository;
import com.example.Pethood.CoreLayer.Application.Repository.VeterinaireRepo;
import com.example.Pethood.CoreLayer.Application.Services.Adresse.AdresseService;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Utilisateur.Veterinaire;
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
public class VeterinaireServiceImpl  implements VeterinaireService{

    private VeterinaireRepo veterinaireRepo;
    private PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private AdresseService adresseService;

    @Override
    public Veterinaire createVeterinaire(Map<String, Object> requestBody) {

        String email = (String) requestBody.get("email");
        String password = (String) requestBody.get("password");
        String firstname = (String) requestBody.get("firstname");
        String lastname = (String) requestBody.get("lastname");
        String phoneNumber = (String) requestBody.get("phoneNumber");

        // Reuse the existing method to create Adresse
        Adresse address = adresseService.addNewAdresse(
                (String) requestBody.get("address"),
                (String) requestBody.get("complementAddress"),
                (String) requestBody.get("codePostal"),
                (String) requestBody.get("city"),
                (String) requestBody.get("country")
        );

        String specialite = (String) requestBody.get("specialite");
        String adresseCabinet = (String) requestBody.get("adresseCabinet");
        String telephoneCabinet = (String) requestBody.get("telephoneCabinet");

        Veterinaire veterinaire = Veterinaire.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .firstname(firstname)
                .lastname(lastname)
                .phoneNumber(phoneNumber)
                .address(address)  // Set the Adresse object
                .Specialite(specialite)
                .AdresseCabinet(adresseCabinet)
                .TelephoneCabinet(telephoneCabinet)
                .build();

        Role role  =  roleRepository.findByRoleName("Vetirinaire");
        List<Role> roleList = veterinaire.getRoleList();
        if (roleList == null) {
            roleList = new ArrayList<>();
            veterinaire.setRoleList(roleList);
        }
        veterinaire.getRoleList().add(role);

        return veterinaireRepo.save(veterinaire);

    }
}
