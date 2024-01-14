package raf.sk.drugiprojekat.korisnickiservis.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import raf.sk.drugiprojekat.korisnickiservis.domain.Client;
import raf.sk.drugiprojekat.korisnickiservis.dto.*;
import raf.sk.drugiprojekat.korisnickiservis.exception.NotFoundException;
import raf.sk.drugiprojekat.korisnickiservis.mapper.ClientMapper;
import raf.sk.drugiprojekat.korisnickiservis.repository.ClientRepository;
import raf.sk.drugiprojekat.korisnickiservis.security.service.TokenService;
import raf.sk.drugiprojekat.korisnickiservis.service.ClientService;

import javax.transaction.Transactional;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private TokenService tokenService;
    private ClientRepository clientRepository;
    private ClientMapper clientMapper;

    public ClientServiceImpl(ClientRepository clientRepository, TokenService tokenService, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.tokenService = tokenService;
        this.clientMapper = clientMapper;
    }

    @Override
    public Page<ClientDto> findAll(Pageable pageable) {
        return clientRepository.findAll(pageable).map(clientMapper::clientToClientDto);
    }

    @Override
    public ClientDto findById(Long id) {
        return clientRepository.findById(id).map(clientMapper::clientToClientDto).orElseThrow(() -> new NotFoundException(String.format("CLIENT: [id: %d] NOT FOUND.", id)));
    }

    @Override
    public ClientDto update(Long id, ClientUpdateDto clientUpdateDto) {
        // You can add a method clientUpdateDtoToClient in the ClientMapper class and have this batch of code only in one place (as in add method)
        Client client = clientRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("CLIENT: [id: %d] NOT FOUND.", id)));
        client.setUsername(clientUpdateDto.getUsername());
        client.setPassword(clientUpdateDto.getPassword());
        client.setEmail(clientUpdateDto.getEmail());
        client.setBirthDate(clientUpdateDto.getBirthDate());
        client.setName(clientUpdateDto.getName());
        client.setSurname(clientUpdateDto.getSurname());
        return clientMapper.clientToClientDto(clientRepository.save(client));
    }

    @Override
    public ClientDto add(ClientCreateDto clientCreateDto) {
        return clientMapper.clientToClientDto(clientMapper.clientCreateDtoToClient(clientCreateDto));
    }

    @Override
    public TokenResponseDto login(TokenRequestDto tokenRequestDto) {
        Client client = clientRepository.findClientByUsernameAndPassword(tokenRequestDto.getUsername(), tokenRequestDto.getPassword())
                .orElseThrow(() -> new NotFoundException(String.format("CLIENT: [username: %s, password: %s] NOT FOUND.", tokenRequestDto.getUsername(), tokenRequestDto.getPassword())));
        Claims claims = Jwts.claims();
        claims.put("role", "ROLE_CLIENT");
        claims.put("client_id", client.getId());
        return new TokenResponseDto(tokenService.generate(claims));
    }
}
