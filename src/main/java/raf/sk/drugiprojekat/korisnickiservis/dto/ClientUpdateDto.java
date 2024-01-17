package raf.sk.drugiprojekat.korisnickiservis.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
