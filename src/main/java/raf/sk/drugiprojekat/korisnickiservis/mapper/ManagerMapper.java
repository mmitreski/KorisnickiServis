package raf.sk.drugiprojekat.korisnickiservis.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import raf.sk.drugiprojekat.korisnickiservis.domain.Client;
import raf.sk.drugiprojekat.korisnickiservis.domain.Manager;
import raf.sk.drugiprojekat.korisnickiservis.dto.*;
import raf.sk.drugiprojekat.korisnickiservis.exception.NotFoundException;
import raf.sk.drugiprojekat.korisnickiservis.repository.ManagerRepository;

@Component
@AllArgsConstructor
public class ManagerMapper {
    private ManagerRepository managerRepository;
    public ManagerDto managerToManagerDto(Manager manager) {
        ManagerDto managerDto = new ManagerDto();
        managerDto.setId(manager.getId());
        managerDto.setEmail(manager.getEmail());
        managerDto.setName(manager.getName());
        managerDto.setSurname(manager.getSurname());
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

    public Manager managerUpdateDtoToManager(Long id, ManagerUpdateDto managerUpdateDto) {
        Manager manager = managerRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("MANAGER: [id: %d] NOT FOUND.", id)));
        if(managerUpdateDto.getBirthDate() != null)
            manager.setBirthDate(managerUpdateDto.getBirthDate());
        if(managerUpdateDto.getEmail() != null)
            manager.setEmail(managerUpdateDto.getEmail());
        if(managerUpdateDto.getUsername() != null)
            manager.setUsername(managerUpdateDto.getUsername());
        if(managerUpdateDto.getPassword() != null)
            manager.setPassword(managerUpdateDto.getPassword());
        if(managerUpdateDto.getName() != null)
            manager.setName(managerUpdateDto.getName());
        if(managerUpdateDto.getSurname() != null)
            manager.setSurname(managerUpdateDto.getSurname());
        if(managerUpdateDto.getGymName() != null)
            manager.setGymName(managerUpdateDto.getGymName());
        return manager;
    }
}
