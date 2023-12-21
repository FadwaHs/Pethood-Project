package com.example.Pethood.CoreLayer.Application.Repository;

import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Publication.Animal;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Utilisateur.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository  extends JpaRepository<Animal, Long> {
}
