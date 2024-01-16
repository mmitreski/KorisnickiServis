package raf.sk.drugiprojekat.korisnickiservis.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import raf.sk.drugiprojekat.korisnickiservis.dto.*;

public interface ManagerService {
    Page<ManagerDto> findAll(Pageable pageable);
    ManagerDto findById(Long id);
    ManagerDto update(Long id, ManagerUpdateDto managerUpdateDto);
    ManagerDto add(ManagerCreateDto managerCreateDto);
    TokenResponseDto login(TokenRequestDto tokenRequestDto);
    void deleteById(Long id);
}
