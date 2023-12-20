package com.example.Pethood.CoreLayer.Application.Repository;

import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Utilisateur.Particulier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticulieRepo extends JpaRepository<Particulier, Long> {

}
