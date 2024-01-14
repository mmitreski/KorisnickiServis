package raf.sk.drugiprojekat.korisnickiservis.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import raf.sk.drugiprojekat.korisnickiservis.domain.Admin;
import raf.sk.drugiprojekat.korisnickiservis.dto.AdminDto;
import raf.sk.drugiprojekat.korisnickiservis.dto.AdminUpdateDto;
import raf.sk.drugiprojekat.korisnickiservis.dto.TokenRequestDto;
import raf.sk.drugiprojekat.korisnickiservis.dto.TokenResponseDto;
import raf.sk.drugiprojekat.korisnickiservis.exception.NotFoundException;
import raf.sk.drugiprojekat.korisnickiservis.mapper.AdminMapper;
import raf.sk.drugiprojekat.korisnickiservis.repository.AdminRepository;
import raf.sk.drugiprojekat.korisnickiservis.security.service.TokenService;
import raf.sk.drugiprojekat.korisnickiservis.service.AdminService;

import javax.transaction.Transactional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    private TokenService tokenService;
    private AdminRepository adminRepository;
    private AdminMapper adminMapper;

    public AdminServiceImpl(TokenService tokenService, AdminRepository adminRepository, AdminMapper adminMapper) {
        this.tokenService = tokenService;
        this.adminRepository = adminRepository;
        this.adminMapper = adminMapper;
    }

    @Override
    public Page<AdminDto> findAll(Pageable pageable) {
        return adminRepository.findAll(pageable).map(adminMapper::adminToAdminDto);
    }

    @Override
    public AdminDto findById(Long id) {
        return adminRepository.findById(id).map(adminMapper::adminToAdminDto).orElseThrow(() -> new NotFoundException(String.format("ADMIN: [id: %d] NOT FOUND.", id)));
    }

    @Override
    public AdminDto update(Long id, AdminUpdateDto adminUpdateDto) {
        Admin admin = adminRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("ADMIN: [id: %d] NOT FOUND.", id)));
        admin.setUsername(adminUpdateDto.getUsername());
        admin.setPassword(adminUpdateDto.getPassword());
        admin.setName(adminUpdateDto.getName());
        admin.setSurname(adminUpdateDto.getSurname());
        admin.setBirthDate(admin.getBirthDate());
        admin.setEmail(admin.getEmail());
        return adminMapper.adminToAdminDto(adminRepository.save(admin));
    }

    @Override
    public TokenResponseDto login(TokenRequestDto tokenRequestDto) {
        Admin admin = adminRepository.findAdminByUsernameAndPassword(tokenRequestDto.getUsername(), tokenRequestDto.getPassword()).orElseThrow(() -> new NotFoundException(String.format("ADMIN: [username: %s, password: %s] NOT FOUND.", tokenRequestDto.getUsername(), tokenRequestDto.getPassword())));
        Claims claims = Jwts.claims();
        claims.put("role", "ROLE_ADMIN");
        claims.put("admin_id", admin.getId());
        return new TokenResponseDto(tokenService.generate(claims));
    }
}
