package com.example.Pethood.CoreLayer.BusinessObjects.Entities.Publication;

import com.example.Pethood.CoreLayer.BusinessObjects.ValueObjects.GalerieImages;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_publication")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Publication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "titre may not be blank")
    private String Titre;

    @NotBlank(message = "Description may not be blank")
    private String Description;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Date date_publication;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;


    @OneToMany(mappedBy = "publication",cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<GalerieImages> galerieImages = new ArrayList<>();

}
