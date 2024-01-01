package com.example.Pethood.CoreLayer.Application.Repository;

import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Publication.Perte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerteRepository  extends JpaRepository<Perte, Long> {

}
