package com.example.Pethood.CoreLayer.BusinessObjects.Entities.Publication;

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
@Builder
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Name;
    private String Type;
    private String Sexe;
    private Double Age;
    private String Sante;
    private  String Race;
    @ColumnDefault("false")
    private boolean isVaccinated;
    private String Lieu;

    @JsonBackReference("publication_animal")
    @OneToMany(mappedBy = "animal",cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Publication> publicationList = new ArrayList<>();

}
