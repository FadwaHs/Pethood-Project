package com.example.Pethood.CoreLayer.BusinessObjects.Entities.Multimedia;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_multimedia")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public abstract  class MultimediaPublication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "titre may not be blank")
    private String Titre;

    @NotBlank(message = "Description may not be blank")
    private String Description;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Date date_publication;

    private String Image;
}
