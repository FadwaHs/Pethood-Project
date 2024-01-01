package com.example.Pethood.CoreLayer.BusinessObjects.Entities.Publication;


import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Utilisateur.Particulier;
import com.example.Pethood.CoreLayer.BusinessObjects.Enum.AlerteSoinStatus;
import com.example.Pethood.CoreLayer.BusinessObjects.Enum.PerteStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Perte extends  Publication{

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Date DateDisparition;
    private String LieuDisparition;
    private String ProprietaireAnimal;
    private String ContactSOS;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @NotNull
    private PerteStatus status = PerteStatus.NOUVEAU;

    @JsonManagedReference("particulier_perte")
    @ManyToOne
    @JoinColumn(name = "particulier_id")
    private Particulier particulier;

}
