package com.example.Pethood.CoreLayer.BusinessObjects.ValueObjects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Role {

    @Id
    private String roleName;
}