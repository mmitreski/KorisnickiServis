package raf.sk.drugiprojekat.korisnickiservis.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter @Setter
public class ManagerCreateDto {
    @Email
    private String email;
    @Length(min = 4, max = 13)
    private String username;
    @Length(min = 7, max = 15)
    private String password;
    @NotNull
    private LocalDate birthDate;
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    private String gymName;
    @NotNull
    private LocalDate employmentDate;
}
