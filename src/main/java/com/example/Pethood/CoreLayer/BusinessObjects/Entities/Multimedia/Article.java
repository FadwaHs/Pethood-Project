package com.example.Pethood.CoreLayer.BusinessObjects.Entities.Multimedia;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Article extends  MultimediaPublication{

    private String CategorieArticle;
    private String Source;
    private  String Auteur;
}
