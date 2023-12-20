package com.example.Pethood.CoreLayer.BusinessObjects.Entities.Publication;


import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Perte extends  Publication{


    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Date DateDisparition;

    private String LieuDisparition;

    private String ProprietaireAnimal;
    private String ContactSOS;

}
