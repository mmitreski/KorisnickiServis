package raf.sk.drugiprojekat.korisnickiservis.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import raf.sk.drugiprojekat.korisnickiservis.dto.ManagerCreateDto;
import raf.sk.drugiprojekat.korisnickiservis.dto.ManagerDto;
import raf.sk.drugiprojekat.korisnickiservis.dto.TokenRequestDto;
import raf.sk.drugiprojekat.korisnickiservis.dto.TokenResponseDto;

public interface ManagerService {
    Page<ManagerDto> findAll(Pageable pageable);
    ManagerDto add(ManagerCreateDto managerCreateDto);
    TokenResponseDto login(TokenRequestDto tokenRequestDto);
}
