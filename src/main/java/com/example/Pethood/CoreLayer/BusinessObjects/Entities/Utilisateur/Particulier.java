package com.example.Pethood.CoreLayer.BusinessObjects.Entities.Utilisateur;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Publication.Adoption;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Publication.DemandeAdoption;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class Particulier extends  Utilisateur {


    private String DateNaissance;
    private String Sexe;


    @OneToMany(mappedBy = "particulier",cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Adoption> adoptionList = new ArrayList<>();


    @OneToMany(mappedBy = "particulier",cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<DemandeAdoption> demandeAdoptions = new ArrayList<>();



}
