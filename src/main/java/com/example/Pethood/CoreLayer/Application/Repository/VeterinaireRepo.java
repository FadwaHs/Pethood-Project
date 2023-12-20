package com.example.Pethood.CoreLayer.Application.Repository;

import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Utilisateur.Organisation;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Utilisateur.Veterinaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeterinaireRepo extends JpaRepository<Veterinaire, Long> {

}
