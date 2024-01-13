package raf.sk.drugiprojekat.korisnickiservis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raf.sk.drugiprojekat.korisnickiservis.domain.Manager;

import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Optional<Manager> findManagerByUsernameAndPassword(String username, String password);
    Optional<Manager> findManagerByUsername(String username);
    Optional<Manager> findManagerById(Long id);
    Optional<Manager> findAllByGymName(String gymName);
}
