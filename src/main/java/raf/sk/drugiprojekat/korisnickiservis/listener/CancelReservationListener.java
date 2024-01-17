package raf.sk.drugiprojekat.korisnickiservis.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import lombok.AllArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import raf.sk.drugiprojekat.korisnickiservis.listener.helper.MessageHelper;
import raf.sk.drugiprojekat.korisnickiservis.service.ClientService;

@Component
@AllArgsConstructor
public class CancelReservationListener {
    private MessageHelper messageHelper;
    private ClientService clientService;

    @JmsListener(destination = "${destination.createClientCancelUser}", concurrency = "5-10")
    public void addReservation(Message message) throws JMSException, JsonProcessingException {
        Long clientId = messageHelper.getMessage(message, Long.class);
        clientService.cancelTraining(clientId);
    }
}
