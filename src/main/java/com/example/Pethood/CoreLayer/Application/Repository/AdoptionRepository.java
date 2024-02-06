package com.example.Pethood.CoreLayer.Application.Repository;

import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Publication.Adoption;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdoptionRepository  extends JpaRepository<Adoption, Long> {

    @Query("SELECT p FROM Adoption p WHERE " +
            "CONCAT(p.animal.Race, ' ', p.animal.Type, ' ', p.animal.Sexe, ' ', CAST(p.animal.Age AS string),' ',p.animal.Sante) LIKE %:data%")
    List<Adoption> findByDataContaining(@Param("data") String data);
}
