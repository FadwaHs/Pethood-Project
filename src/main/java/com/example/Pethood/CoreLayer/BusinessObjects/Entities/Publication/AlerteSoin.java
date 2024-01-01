package com.example.Pethood.CoreLayer.BusinessObjects.Entities.Publication;


import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Utilisateur.Particulier;
import com.example.Pethood.CoreLayer.BusinessObjects.Enum.AdoptionStatus;
import com.example.Pethood.CoreLayer.BusinessObjects.Enum.AlerteSoinStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class AlerteSoin extends Publication{

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @NotNull
    private AlerteSoinStatus status = AlerteSoinStatus.NOUVEAU;

    private String DescriptionMaladie;
    private Double CoûtTraitement;

    @JsonManagedReference("particulier_AlerteSoin")
    @ManyToOne
    @JoinColumn(name = "particulier_id")
    private Particulier particulier;
}
