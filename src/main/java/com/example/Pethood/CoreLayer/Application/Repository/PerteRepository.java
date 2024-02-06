package com.example.Pethood.CoreLayer.Application.Repository;

import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Publication.AlerteSoin;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Publication.Perte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PerteRepository  extends JpaRepository<Perte, Long> {

    @Query("SELECT p FROM Perte p WHERE " +
            "CONCAT(p.animal.Race, ' ', p.animal.Type, ' ', p.animal.Sexe, ' ', CAST(p.animal.Age AS string),' ',p.animal.Sante) LIKE %:data%")
    List<Perte> findByDataContaining(@Param("data") String data);

}
