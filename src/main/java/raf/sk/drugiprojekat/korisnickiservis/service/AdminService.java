package raf.sk.drugiprojekat.korisnickiservis.service;

import raf.sk.drugiprojekat.korisnickiservis.domain.Manager;
import raf.sk.drugiprojekat.korisnickiservis.dto.*;

public interface AdminService {
    AdminDto findById(Long id);
    AdminDto update(Long id, AdminUpdateDto adminUpdateDto);
    TokenResponseDto login(TokenRequestDto tokenRequestDto);
}
