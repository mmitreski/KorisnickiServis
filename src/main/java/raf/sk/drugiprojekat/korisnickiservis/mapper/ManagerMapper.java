package raf.sk.drugiprojekat.korisnickiservis.mapper;

import org.springframework.stereotype.Component;
import raf.sk.drugiprojekat.korisnickiservis.domain.Client;
import raf.sk.drugiprojekat.korisnickiservis.domain.Manager;
import raf.sk.drugiprojekat.korisnickiservis.dto.ClientCreateDto;
import raf.sk.drugiprojekat.korisnickiservis.dto.ClientDto;
import raf.sk.drugiprojekat.korisnickiservis.dto.ManagerCreateDto;
import raf.sk.drugiprojekat.korisnickiservis.dto.ManagerDto;

@Component
public class ManagerMapper {
    public ManagerDto managerToManagerDto(Manager manager) {
        ManagerDto managerDto = new ManagerDto();
        managerDto.setId(manager.getId());
        managerDto.setEmail(manager.getEmail());
        managerDto.setName(manager.getName());
        managerDto.setSurname(manager.getSurname());
        managerDto.setForbidden(manager.getForbidden());
        managerDto.setUsername(manager.getUsername());
        managerDto.setBirthDate(manager.getBirthDate());
        managerDto.setEmploymentDate(manager.getEmploymentDate());
        managerDto.setGymName(manager.getGymName());
        return managerDto;
    }

    public Manager managerCreateDtoToManager(ManagerCreateDto managerCreateDto) {
        Manager manager = new Manager();
        manager.setBirthDate(managerCreateDto.getBirthDate());
        manager.setEmail(managerCreateDto.getEmail());
        manager.setName(managerCreateDto.getName());
        manager.setSurname(managerCreateDto.getSurname());
        manager.setUsername(managerCreateDto.getUsername());
        manager.setPassword(managerCreateDto.getPassword());
        manager.setEmploymentDate(managerCreateDto.getEmploymentDate());
        manager.setGymName(managerCreateDto.getGymName());
        return manager;
    }
}
