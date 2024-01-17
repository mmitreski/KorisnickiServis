package raf.sk.drugiprojekat.korisnickiservis.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("Client")
@Getter @Setter @NoArgsConstructor
public class Client extends User{
    private Long cardId;
    private Long trainingsReserved;
    private Boolean forbidden;
    private Boolean confirmed;
    public Client(String username, String password, String email, LocalDate birthDate, String name, String surname, Long cardId, Long trainingsReserved, Boolean forbidden) {
        super(username, password, email, birthDate, name, surname);
        this.cardId = cardId;
        this.trainingsReserved = trainingsReserved;
        this.forbidden = forbidden;
    }
}
