package com.example.Pethood.CoreLayer.BusinessObjects.Entities.Utilisateur;


import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Data
public class Admin extends  Utilisateur{

}
