package raf.sk.drugiprojekat.korisnickiservis.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter @Setter
public class ClientCreateDto {
    @Email
    private String email;
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotNull
    private LocalDate birthDate;
    @Length(min = 4, max = 13)
    private String username;
    @Length(min = 7, max = 15)
    private String password;
    @Range(min = 1)
    private Long cardId;
}
