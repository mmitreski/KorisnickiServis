package raf.sk.drugiprojekat.korisnickiservis.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ClaimsBuilder;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import raf.sk.drugiprojekat.korisnickiservis.domain.Client;
import raf.sk.drugiprojekat.korisnickiservis.dto.*;
import raf.sk.drugiprojekat.korisnickiservis.exception.NotFoundException;
import raf.sk.drugiprojekat.korisnickiservis.listener.helper.MessageHelper;
import raf.sk.drugiprojekat.korisnickiservis.mapper.ClientMapper;
import raf.sk.drugiprojekat.korisnickiservis.repository.ClientRepository;
import raf.sk.drugiprojekat.korisnickiservis.security.service.TokenService;
import raf.sk.drugiprojekat.korisnickiservis.service.ClientService;

import jakarta.transaction.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private TokenService tokenService;
    private ClientRepository clientRepository;
    private ClientMapper clientMapper;
    private JmsTemplate jmsTemplate;
    private MessageHelper messageHelper;
    @Value("${destination.sendEmails}")
    private String activationEmail;

    public ClientServiceImpl(ClientRepository clientRepository, TokenService tokenService, ClientMapper clientMapper, JmsTemplate jmsTemplate, MessageHelper messageHelper) {
        this.clientRepository = clientRepository;
        this.tokenService = tokenService;
        this.clientMapper = clientMapper;
        this.jmsTemplate = jmsTemplate;
        this.messageHelper = messageHelper;
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
        return clientMapper.clientToClientDto(clientRepository.save(clientMapper.clientUpdateDtoToClient(id, clientUpdateDto)));
    }

    @Override
    public ClientDto add(ClientCreateDto clientCreateDto) {
        ClientDto clientDto = clientMapper.clientToClientDto(clientRepository.save(clientMapper.clientCreateDtoToClient(clientCreateDto)));
        String subject = "Aktivacija naloga";
        String content = "Postovani, molimo Vas da aktivirate vas nalog na linku: http://localhost:8080/client/confirm?id=" + clientDto.getId() + ". Kada aktivirate nalog, mocicete da zakazujete treninge.";
        jmsTemplate.convertAndSend(activationEmail, new EmailCreateDto(clientCreateDto.getEmail(), LocalDateTime.now(), subject, content));
        return clientDto;
    }

    @Override
    public TokenResponseDto login(TokenRequestDto tokenRequestDto) {
        Client client = clientRepository.findClientByUsernameAndPassword(tokenRequestDto.getUsername(), tokenRequestDto.getPassword())
                .orElseThrow(() -> new NotFoundException(String.format("CLIENT: [username: %s, password: %s] NOT FOUND.", tokenRequestDto.getUsername(), tokenRequestDto.getPassword())));
        ClaimsBuilder claims = Jwts.claims();
        claims.add("role", "ROLECLIENT");
        claims.add("clientid", client.getId());
        return new TokenResponseDto(tokenService.generate(claims.build()));
    }

    @Override
    public void forbiddenById(Long id, Boolean forbidden) {
        clientRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("CLIENT: [id: %d] NOT FOUND.", id))).setForbidden(forbidden);
    }

    @Override
    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public void confirmRegister(Long id) {
        Client client = clientRepository.findById(id).get();
        client.setConfirmed(true);
        clientRepository.save(client);
    }

    @Override
    public void reserveTraining(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("CLIENT: [id: %d] NOT FOUND.", id)));
        client.setTrainingsReserved(client.getTrainingsReserved()+1);
        clientRepository.save(client);
    }

    @Override
    public void cancelTraining(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("CLIENT: [id: %d] NOT FOUND.", id)));
        client.setTrainingsReserved(client.getTrainingsReserved()-1);
        clientRepository.save(client);
    }
}
