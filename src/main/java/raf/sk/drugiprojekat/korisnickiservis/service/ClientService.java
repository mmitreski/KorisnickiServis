package raf.sk.drugiprojekat.korisnickiservis.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import raf.sk.drugiprojekat.korisnickiservis.dto.*;

public interface ClientService {
    Page<ClientDto> findAll(Pageable pageable);
    ClientDto findById(Long id);
    ClientDto update(Long id, ClientUpdateDto clientUpdateDto);
    ClientDto add(ClientCreateDto clientCreateDto);
    TokenResponseDto login(TokenRequestDto tokenRequestDto);
    void forbiddenById(Long id, Boolean forbidden);
    void deleteById(Long id);
}
