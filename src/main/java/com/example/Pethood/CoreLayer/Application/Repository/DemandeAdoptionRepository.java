package com.example.Pethood.CoreLayer.Application.Repository;

import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Multimedia.Evenement;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Publication.DemandeAdoption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemandeAdoptionRepository extends JpaRepository<DemandeAdoption, Long> {

}
