package com.example.Pethood.CoreLayer.BusinessObjects.Entities.Publication;


import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Utilisateur.Organisation;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Utilisateur.Particulier;
import com.example.Pethood.CoreLayer.BusinessObjects.Enum.AdoptionStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Adoption extends  Publication{


    private String Infos;
    private Double Frais_Adoption;
    @Enumerated(EnumType.STRING)
    @NotNull
    private AdoptionStatus status = AdoptionStatus.NOUVEAU;

    @ManyToOne
    @JoinColumn(name = "particulier_id")
    private Particulier particulier;

    @ManyToOne
    @JoinColumn(name = "organisation_id")
    private Organisation organisation;



    @OneToMany(mappedBy = "adoption",cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<DemandeAdoption> demandeAdoptions = new ArrayList<>();

}
