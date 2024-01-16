package raf.sk.drugiprojekat.korisnickiservis.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter @Setter
public class AdminUpdateDto {
    @Email
    private String email;
    @Length(min = 4, max = 13)
    private String username;
    @Length(min = 7, max = 15)
    private String password;
    private LocalDate birthDate;
    private String name;
    private String surname;
}

