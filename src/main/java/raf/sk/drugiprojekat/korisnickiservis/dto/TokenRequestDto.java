package raf.sk.drugiprojekat.korisnickiservis.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class TokenRequestDto {
    private String username;
    private String password;

}
