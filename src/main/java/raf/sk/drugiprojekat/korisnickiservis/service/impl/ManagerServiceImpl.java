package raf.sk.drugiprojekat.korisnickiservis.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ClaimsBuilder;
import io.jsonwebtoken.Jwts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import raf.sk.drugiprojekat.korisnickiservis.domain.Manager;
import raf.sk.drugiprojekat.korisnickiservis.dto.*;
import raf.sk.drugiprojekat.korisnickiservis.exception.NotFoundException;
import raf.sk.drugiprojekat.korisnickiservis.mapper.ManagerMapper;
import raf.sk.drugiprojekat.korisnickiservis.repository.ManagerRepository;
import raf.sk.drugiprojekat.korisnickiservis.security.service.TokenService;
import raf.sk.drugiprojekat.korisnickiservis.service.ManagerService;

import jakarta.transaction.Transactional;

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
        return managerRepository.findAll(pageable).map(managerMapper::managerToManagerDto);
    }
    @Override
    public ManagerDto findById(Long id) {
        return managerRepository.findById(id).map(managerMapper::managerToManagerDto).orElseThrow(() -> new NotFoundException(String.format("MANAGER: [id: %d] NOT FOUND.", id)));
    }
    @Override
    public ManagerDto update(Long id, ManagerUpdateDto managerUpdateDto) {
        return managerMapper.managerToManagerDto(managerRepository.save(managerMapper.managerUpdateDtoToManager(id, managerUpdateDto)));
    }
    @Override
    public ManagerDto add(ManagerCreateDto managerCreateDto) {
        return managerMapper.managerToManagerDto(managerRepository.save(managerMapper.managerCreateDtoToManager(managerCreateDto)));
    }
    @Override
    public TokenResponseDto login(TokenRequestDto tokenRequestDto) {
        Manager manager = managerRepository.findManagerByUsernameAndPassword(tokenRequestDto.getUsername(), tokenRequestDto.getPassword()).orElseThrow(() -> new NotFoundException(String.format("MANAGER: [username: %s, password: %s] NOT FOUND.", tokenRequestDto.getUsername(), tokenRequestDto.getPassword())));
        ClaimsBuilder claims = Jwts.claims();
        claims.add("role", "ROLEMANAGER");
        claims.add("managerid", manager.getId());
        return new TokenResponseDto(tokenService.generate(claims.build()));
    }

    @Override
    public void deleteById(Long id) {
        managerRepository.deleteById(id);
    }
}
