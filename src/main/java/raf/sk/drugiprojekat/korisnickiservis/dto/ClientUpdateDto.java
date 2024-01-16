package raf.sk.drugiprojekat.korisnickiservis.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter @Setter
public class ClientUpdateDto {
    @Email
    private String email;
    private String name;
    private String surname;
    private LocalDate birthDate;
    @Length(min = 4, max = 13)
    private String username;
    @Length(min = 7, max = 15)
    private String password;
}
