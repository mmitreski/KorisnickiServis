package raf.sk.drugiprojekat.korisnickiservis.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import raf.sk.drugiprojekat.korisnickiservis.dto.*;
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
        return null;
    }

    @Override
    public ClientDto update(Long id, ClientUpdateDto clientUpdateDto) {
        return null;
    }

    @Override
    public ClientDto add(ClientCreateDto clientCreateDto) {
        return null;
    }

    @Override
    public TokenResponseDto login(TokenRequestDto tokenRequestDto) {
        return null;
    }
}
