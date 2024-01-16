package raf.sk.drugiprojekat.korisnickiservis.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import raf.sk.drugiprojekat.korisnickiservis.domain.Client;
import raf.sk.drugiprojekat.korisnickiservis.dto.ClientCreateDto;
import raf.sk.drugiprojekat.korisnickiservis.dto.ClientDto;
import raf.sk.drugiprojekat.korisnickiservis.dto.ClientUpdateDto;
import raf.sk.drugiprojekat.korisnickiservis.exception.NotFoundException;
import raf.sk.drugiprojekat.korisnickiservis.repository.ClientRepository;

@Component
@AllArgsConstructor
public class ClientMapper {
    private ClientRepository clientRepository;
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

    public Client clientUpdateDtoToClient(Long id, ClientUpdateDto clientUpdateDto) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("CLIENT: [id: %d] NOT FOUND.", id)));
        if(clientUpdateDto.getBirthDate() != null)
            client.setBirthDate(clientUpdateDto.getBirthDate());
        if(clientUpdateDto.getEmail() != null)
            client.setEmail(clientUpdateDto.getEmail());
        if(clientUpdateDto.getUsername() != null)
            client.setUsername(clientUpdateDto.getUsername());
        if(clientUpdateDto.getPassword() != null)
            client.setPassword(clientUpdateDto.getPassword());
        if(clientUpdateDto.getName() != null)
            client.setName(clientUpdateDto.getName());
        if(clientUpdateDto.getSurname() != null)
            client.setSurname(clientUpdateDto.getSurname());
        return client;
    }
}
