package com.example.Pethood.CoreLayer.BusinessObjects.Entities.Utilisateur;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Multimedia.Evenement;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Publication.Adoption;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
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
public class Organisation extends Utilisateur{


    private String NomStructure;
    private  String IdOfficielStructure;
    private String OrganisationName ;
    private String SiteWeb ;

    @JsonBackReference("organisation_adoption")
    @OneToMany(mappedBy = "organisation",cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Adoption> adoptionList = new ArrayList<>();

    @JsonBackReference("organisation_Evenement")
    @OneToMany(mappedBy = "organisation",cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Evenement> evenementList = new ArrayList<>();



}
