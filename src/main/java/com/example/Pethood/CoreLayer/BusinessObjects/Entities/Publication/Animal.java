package com.example.Pethood.CoreLayer.BusinessObjects.Entities.Publication;

import com.example.Pethood.CoreLayer.BusinessObjects.ValueObjects.GalerieImages;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Type;
    private String Sexe;
    private Double Age;
    private String Sante;
    @ColumnDefault("false")
    private boolean isVaccinated;
    private String Lieu;

    @OneToMany(mappedBy = "animal",cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Publication> publicationList = new ArrayList<>();

}
