package raf.sk.drugiprojekat.korisnickiservis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raf.sk.drugiprojekat.korisnickiservis.domain.Client;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findClientByUsernameAndPassword(String username, String password);
    Optional<Client> findClientByUsername(String username);
    Optional<Client> findClientById(Long id);
    Optional<Client> findClientByCardId(Long cardId);
}
