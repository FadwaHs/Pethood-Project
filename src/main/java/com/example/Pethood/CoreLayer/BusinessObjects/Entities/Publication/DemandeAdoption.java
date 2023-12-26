package com.example.Pethood.CoreLayer.BusinessObjects.Entities.Publication;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Utilisateur.Particulier;
import com.example.Pethood.CoreLayer.BusinessObjects.Enum.AdoptionStatus;
import com.example.Pethood.CoreLayer.BusinessObjects.Enum.DemandesStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Data
@Builder
public class DemandeAdoption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Builder.Default
    private DemandesStatus status = DemandesStatus.NOUVELLE;

    @NotBlank(message = "Description may not be blank")
    private String Description;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Date date_Demande;

    @JsonManagedReference("particulier_demandeAdoptions")
    @ManyToOne
    @JoinColumn(name = "particulier_id")
    private Particulier particulier;

    @JsonBackReference("adoption_demandeAdoptions")
    @ManyToOne
    @JoinColumn(name = "adoption_id")
    private Adoption adoption;
}
