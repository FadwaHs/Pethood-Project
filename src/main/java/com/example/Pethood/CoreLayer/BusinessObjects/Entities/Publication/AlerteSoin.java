package com.example.Pethood.CoreLayer.BusinessObjects.Entities.Publication;


import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlerteSoin extends Publication{



    private String DescriptionMaladie;
    private Double CoûtTraitement;
}
