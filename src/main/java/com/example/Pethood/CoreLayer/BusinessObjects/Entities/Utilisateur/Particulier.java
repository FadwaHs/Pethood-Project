package com.example.Pethood.CoreLayer.BusinessObjects.Entities.Utilisateur;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Publication.Adoption;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Publication.AlerteSoin;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Publication.DemandeAdoption;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Publication.Perte;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @JsonBackReference("particulier_adoption")
    @OneToMany(mappedBy = "particulier",cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Adoption> adoptionList = new ArrayList<>();

    @JsonBackReference("particulier_demandeAdoptions")
    @OneToMany(mappedBy = "particulier",cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<DemandeAdoption> demandeAdoptions = new ArrayList<>();

    @JsonBackReference("particulier_AlerteSoin")
    @OneToMany(mappedBy = "particulier",cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<AlerteSoin> alerteSoinList = new ArrayList<>();

    @JsonBackReference("particulier_perte")
    @OneToMany(mappedBy = "particulier",cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Perte> perteList = new ArrayList<>();


    @PostLoad
    public void initializeCollections() {

        if (this.alerteSoinList == null) {
            this.alerteSoinList = new ArrayList<>();
        } else {
            // remove duplicates
            Set<AlerteSoin> uniqueAlerteSoin = new HashSet<>(this.alerteSoinList);
            this.alerteSoinList.clear();
            this.alerteSoinList.addAll(uniqueAlerteSoin);
        }

        /////////////////////////////////////////////////

        if (this.perteList == null) {
            this.perteList = new ArrayList<>();
        } else {
            // remove duplicates
            Set<Perte> uniqueperte = new HashSet<>(this.perteList);
            this.perteList.clear();
            this.perteList.addAll(uniqueperte);
        }

        //////////////////////////////////////////////

        if (this.adoptionList == null) {
            this.adoptionList = new ArrayList<>();
        } else {
            // remove duplicates
            Set<Adoption> uniqueAdoption = new HashSet<>(this.adoptionList);
            this.adoptionList.clear();
            this.adoptionList.addAll(uniqueAdoption);
        }
    }





}
