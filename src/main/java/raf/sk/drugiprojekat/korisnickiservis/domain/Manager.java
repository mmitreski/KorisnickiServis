package raf.sk.drugiprojekat.korisnickiservis.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("Manager")
@Getter @Setter @NoArgsConstructor
public class Manager extends User{

    private String gymName;
    private LocalDate employmentDate;

    public Manager(String username, String password, String email, LocalDate birthDate, String name, String surname, String gymName, LocalDate employmentDate) {
        super(username, password, email, birthDate, name, surname);
        this.gymName = gymName;
        this.employmentDate = employmentDate;
    }
}
