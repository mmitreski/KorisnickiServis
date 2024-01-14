package raf.sk.drugiprojekat.korisnickiservis.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import raf.sk.drugiprojekat.korisnickiservis.domain.Manager;
import raf.sk.drugiprojekat.korisnickiservis.dto.*;

public interface AdminService {
    Page<AdminDto> findAll(Pageable pageable);
    AdminDto findById(Long id);
    AdminDto update(Long id, AdminUpdateDto adminUpdateDto);
    TokenResponseDto login(TokenRequestDto tokenRequestDto);
}
