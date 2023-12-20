package com.example.Pethood.CoreLayer.BusinessObjects.ValueObjects;

import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Publication.Publication;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Utilisateur.Particulier;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GalerieImages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String URL;

    @ManyToOne
    @JoinColumn(name = "publication_id")
    private Publication publication;
}
