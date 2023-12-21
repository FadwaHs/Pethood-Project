package com.example.Pethood.CoreLayer.BusinessObjects.Entities.Multimedia;

import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Utilisateur.Organisation;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class Evenement extends MultimediaPublication {

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Date date_Event;

    private  String LieuEvenement;
    private String TypeEvenement;

    private String Organisateur;

    private  String Intervenants;

    @ManyToOne
    @JoinColumn(name = "organisation_id")
    private Organisation organisation;




}
