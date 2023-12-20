package com.example.Pethood.CoreLayer.BusinessObjects.Entities.Utilisateur;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class Veterinaire extends  Utilisateur{

    private String Specialite;
    private String  AdresseCabinet;
    private  String TelephoneCabinet;
}
