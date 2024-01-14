package raf.sk.drugiprojekat.korisnickiservis.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;
@MappedSuperclass
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "UserType")
@Getter @Setter @NoArgsConstructor
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    @Email @Column(unique = true)
    private String email;
    private LocalDate birthDate;
    private String name;
    private String surname;

    User(String username, String password, String email, LocalDate birthDate, String name, String surname) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.birthDate = birthDate;
        this.name = name;
        this.surname = surname;
    }

}
