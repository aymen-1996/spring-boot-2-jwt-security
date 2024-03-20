package springBoot2.Secutity.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.TABLE)
        private Long id;
        private String nom;
        private String image;
        private String prenom;
        @JsonIgnore
        private String password;
        @Column(unique = true)
        private String mail;
        private Integer numtel;
        private String role;




}
