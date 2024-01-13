package raf.sk.drugiprojekat.korisnickiservis.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import raf.sk.drugiprojekat.korisnickiservis.dto.ManagerCreateDto;
import raf.sk.drugiprojekat.korisnickiservis.dto.ManagerDto;
import raf.sk.drugiprojekat.korisnickiservis.dto.TokenRequestDto;
import raf.sk.drugiprojekat.korisnickiservis.dto.TokenResponseDto;
import raf.sk.drugiprojekat.korisnickiservis.mapper.ManagerMapper;
import raf.sk.drugiprojekat.korisnickiservis.repository.ManagerRepository;
import raf.sk.drugiprojekat.korisnickiservis.security.service.TokenService;
import raf.sk.drugiprojekat.korisnickiservis.service.ManagerService;

import javax.transaction.Transactional;

@Service
@Transactional
public class ManagerServiceImpl implements ManagerService {

    private TokenService tokenService;
    private ManagerRepository managerRepository;
    private ManagerMapper managerMapper;

    public ManagerServiceImpl(ManagerRepository managerRepository, TokenService tokenService, ManagerMapper managerMapper) {
        this.managerRepository = managerRepository;
        this.tokenService = tokenService;
        this.managerMapper = managerMapper;
    }

    @Override
    public Page<ManagerDto> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public ManagerDto add(ManagerCreateDto managerCreateDto) {
        return null;
    }

    @Override
    public TokenResponseDto login(TokenRequestDto tokenRequestDto) {
        return null;
    }
}
