package raf.sk.drugiprojekat.korisnickiservis.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import raf.sk.drugiprojekat.korisnickiservis.domain.Admin;
import raf.sk.drugiprojekat.korisnickiservis.dto.AdminDto;
import raf.sk.drugiprojekat.korisnickiservis.dto.AdminUpdateDto;
import raf.sk.drugiprojekat.korisnickiservis.exception.NotFoundException;
import raf.sk.drugiprojekat.korisnickiservis.repository.AdminRepository;

@Component
@AllArgsConstructor
public class AdminMapper {
    private AdminRepository adminRepository;
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

    public Admin adminUpdateDtoToAdmin(Long id, AdminUpdateDto adminUpdateDto) {
        Admin admin = adminRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("ADMIN: [id: %d] NOT FOUND.", id)));
        if(adminUpdateDto.getEmail() != null)
            admin.setEmail(adminUpdateDto.getEmail());
        if(adminUpdateDto.getName() != null)
            admin.setName(adminUpdateDto.getName());
        if(adminUpdateDto.getSurname() != null)
            admin.setSurname(adminUpdateDto.getSurname());
        if(adminUpdateDto.getUsername() != null)
            admin.setUsername(adminUpdateDto.getUsername());
        if(adminUpdateDto.getPassword() != null)
            admin.setPassword(adminUpdateDto.getPassword());
        if(adminUpdateDto.getBirthDate() != null)
            admin.setBirthDate(adminUpdateDto.getBirthDate());
        return admin;
    }
}
