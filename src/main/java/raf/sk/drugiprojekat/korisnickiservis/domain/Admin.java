package raf.sk.drugiprojekat.korisnickiservis.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("Admin")
@Getter @Setter @NoArgsConstructor
public class Admin extends User{
    public Admin(String username, String password, String email, LocalDate birthDate, String name, String surname) {
        super(username, password, email, birthDate, name, surname);
    }
}
