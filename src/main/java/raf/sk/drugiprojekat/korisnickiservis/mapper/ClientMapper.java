package raf.sk.drugiprojekat.korisnickiservis.mapper;

import org.springframework.stereotype.Component;
import raf.sk.drugiprojekat.korisnickiservis.domain.Client;
import raf.sk.drugiprojekat.korisnickiservis.dto.ClientCreateDto;
import raf.sk.drugiprojekat.korisnickiservis.dto.ClientDto;

@Component
public class ClientMapper {
    public ClientDto clientToClientDto(Client client) {
        ClientDto clientDto = new ClientDto();
        clientDto.setId(client.getId());
        clientDto.setEmail(client.getEmail());
        clientDto.setName(client.getName());
        clientDto.setSurname(client.getSurname());
        clientDto.setCardId(client.getCardId());
        clientDto.setForbidden(client.getForbidden());
        clientDto.setUsername(client.getUsername());
        clientDto.setBirthDate(client.getBirthDate());
        clientDto.setTrainingsReserved(client.getTrainingsReserved());
        return clientDto;
    }

    public Client clientCreateDtoToClient(ClientCreateDto clientCreateDto) {
        Client client = new Client();
        client.setBirthDate(clientCreateDto.getBirthDate());
        client.setEmail(clientCreateDto.getEmail());
        client.setName(clientCreateDto.getName());
        client.setSurname(clientCreateDto.getSurname());
        client.setUsername(clientCreateDto.getUsername());
        client.setPassword(clientCreateDto.getPassword());
        client.setCardId(clientCreateDto.getCardId());
        return client;
    }
}
