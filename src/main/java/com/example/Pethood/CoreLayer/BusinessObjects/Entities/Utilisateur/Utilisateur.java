package com.example.Pethood.CoreLayer.BusinessObjects.Entities.Utilisateur;
import com.example.Pethood.CoreLayer.BusinessObjects.ValueObjects.Adresse;
import com.example.Pethood.CoreLayer.BusinessObjects.ValueObjects.Role;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_utilisateur")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public abstract class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Email may not be blank")
    @Email
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Password may not be blank")
    private String password;

    @NotBlank(message = "username may not be blank")
    private String firstname;

    @NotBlank(message = "lastname may not be blank")
    private String lastname;

    @NotBlank(message = "phoneNumber may not be blank")
    private String phoneNumber;

    @ColumnDefault("false")
    private boolean isActivated;

    @OneToOne
    @JoinColumn(name = "adresse_id")
    private Adresse address;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Role> roleList;

}
