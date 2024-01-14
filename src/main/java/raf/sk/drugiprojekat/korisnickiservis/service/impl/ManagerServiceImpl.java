package raf.sk.drugiprojekat.korisnickiservis.service.impl;

import io.jsonwebtoken.Claims;
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
        return managerRepository.findAll(pageable).map(managerMapper::managerToManagerDto);
    }
    @Override
    public ManagerDto findById(Long id) {
        return managerRepository.findById(id).map(managerMapper::managerToManagerDto).orElseThrow(() -> new NotFoundException(String.format("MANAGER: [id: %d] NOT FOUND.", id)));
    }
    @Override
    public ManagerDto update(Long id, ManagerUpdateDto managerUpdateDto) {
        Manager manager = managerRepository.findManagerById(id).orElseThrow(() -> new NotFoundException(String.format("CLIENT: [id: %d] NOT FOUND.", id)));
        manager.setName(managerUpdateDto.getName());
        manager.setSurname(managerUpdateDto.getSurname());
        manager.setUsername(managerUpdateDto.getUsername());
        manager.setPassword(managerUpdateDto.getPassword());
        manager.setEmail(managerUpdateDto.getEmail());
        manager.setGymName(managerUpdateDto.getGymName());
        manager.setBirthDate(manager.getBirthDate());
        return managerMapper.managerToManagerDto(managerRepository.save(manager));
    }
    @Override
    public ManagerDto add(ManagerCreateDto managerCreateDto) {
        return managerMapper.managerToManagerDto(managerRepository.save(managerMapper.managerCreateDtoToManager(managerCreateDto)));
    }
    @Override
    public TokenResponseDto login(TokenRequestDto tokenRequestDto) {
        Manager manager = managerRepository.findManagerByUsernameAndPassword(tokenRequestDto.getUsername(), tokenRequestDto.getPassword()).orElseThrow(() -> new NotFoundException(String.format("MANAGER: [username: %s, password: %s] NOT FOUND.", tokenRequestDto.getUsername(), tokenRequestDto.getPassword())));
        Claims claims = Jwts.claims();
        claims.put("role", "ROLE_MANAGER");
        claims.put("manager_id", manager.getId());
        return new TokenResponseDto(tokenService.generate(claims));
    }
}
