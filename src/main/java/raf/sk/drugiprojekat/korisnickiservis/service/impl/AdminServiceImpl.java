package raf.sk.drugiprojekat.korisnickiservis.service.impl;

import org.springframework.stereotype.Service;
import raf.sk.drugiprojekat.korisnickiservis.dto.AdminDto;
import raf.sk.drugiprojekat.korisnickiservis.dto.AdminUpdateDto;
import raf.sk.drugiprojekat.korisnickiservis.dto.TokenRequestDto;
import raf.sk.drugiprojekat.korisnickiservis.dto.TokenResponseDto;
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
    public AdminDto findById(Long id) {
        return null;
    }

    @Override
    public AdminDto update(Long id, AdminUpdateDto adminUpdateDto) {
        return null;
    }

    @Override
    public TokenResponseDto login(TokenRequestDto tokenRequestDto) {
        return null;
    }
}
