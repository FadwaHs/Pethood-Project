package com.example.Pethood.CoreLayer.Application.Repository;

import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Multimedia.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EevenementRepository extends JpaRepository<Evenement, Long> {

}
