package com.example.Pethood.CoreLayer.Application.Repository;

import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Publication.Adoption;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Utilisateur.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdoptionRepository  extends JpaRepository<Adoption, Long> {
}
