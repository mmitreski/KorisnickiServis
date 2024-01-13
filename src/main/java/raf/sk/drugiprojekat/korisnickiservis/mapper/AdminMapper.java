package raf.sk.drugiprojekat.korisnickiservis.mapper;

import org.springframework.stereotype.Component;
import raf.sk.drugiprojekat.korisnickiservis.domain.Admin;
import raf.sk.drugiprojekat.korisnickiservis.dto.AdminDto;
import raf.sk.drugiprojekat.korisnickiservis.dto.AdminUpdateDto;
@Component
public class AdminMapper {
    public AdminDto adminToAdminDto(Admin admin) {
        AdminDto adminDto = new AdminDto();
        adminDto.setId(admin.getId());
        adminDto.setEmail(admin.getEmail());
        adminDto.setName(admin.getName());
        adminDto.setSurname(admin.getSurname());
        adminDto.setUsername(admin.getUsername());
        adminDto.setBirthDate(admin.getBirthDate());
        return adminDto;
    }

    public Admin adminUpdateDtoToAdmin(AdminUpdateDto adminUpdateDto) {
        Admin admin = new Admin();
        admin.setEmail(adminUpdateDto.getEmail());
        admin.setName(adminUpdateDto.getName());
        admin.setSurname(adminUpdateDto.getSurname());
        admin.setUsername(adminUpdateDto.getUsername());
        admin.setPassword(adminUpdateDto.getPassword());
        admin.setBirthDate(adminUpdateDto.getBirthDate());
        return admin;
    }
}
