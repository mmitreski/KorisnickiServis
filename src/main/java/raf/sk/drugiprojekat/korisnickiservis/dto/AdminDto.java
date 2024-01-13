package raf.sk.drugiprojekat.korisnickiservis.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter public class AdminDto {
    private Long id;
    private String email;
    private String username;
    private LocalDate birthDate;
    private String name;
    private String surname;
}
