package com.example.Pethood.CoreLayer.Application.Repository;

import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Utilisateur.Organisation;
import com.example.Pethood.CoreLayer.BusinessObjects.ValueObjects.Adresse;
import org.springframework.data.jpa.repository.JpaRepository;




public interface AdresseRepo extends JpaRepository<Adresse, Long> {
}
