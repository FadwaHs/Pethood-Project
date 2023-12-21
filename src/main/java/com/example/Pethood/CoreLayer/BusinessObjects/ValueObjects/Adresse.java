package com.example.Pethood.CoreLayer.BusinessObjects.ValueObjects;

import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Utilisateur.Utilisateur;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Adresse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String address;
    private String complementAddress;
    private String codePostal;
    private String city;
    private String country;

    @JsonBackReference("utilisateur_address")
    @OneToOne(mappedBy = "address",cascade = CascadeType.REMOVE)
    private Utilisateur utilisateur;

}
