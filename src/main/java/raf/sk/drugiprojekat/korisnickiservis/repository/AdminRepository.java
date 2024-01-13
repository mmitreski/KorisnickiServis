package raf.sk.drugiprojekat.korisnickiservis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raf.sk.drugiprojekat.korisnickiservis.domain.Admin;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findAdminByUsernameAndPassword(String username, String password);
    Optional<Admin> findAdminByUsername(String username);
    Optional<Admin> findAdminById(Long id);
}
