package com.example.Pethood.CoreLayer.BusinessObjects.Entities.Publication;


import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Utilisateur.Particulier;
import com.example.Pethood.CoreLayer.BusinessObjects.Enum.AdoptionStatus;
import com.example.Pethood.CoreLayer.BusinessObjects.Enum.DemandesStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DemandeAdoption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Enumerated(EnumType.STRING)
    @NotNull
    private DemandesStatus status = DemandesStatus.NOUVELLE;

    @NotBlank(message = "Description may not be blank")
    private String Description;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Date date_Demande;


    @ManyToOne
    @JoinColumn(name = "particulier_id")
    private Particulier particulier;

    @ManyToOne
    @JoinColumn(name = "adoption_id")
    private Adoption adoption;
}
