package raf.sk.drugiprojekat.korisnickiservis.runner;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import raf.sk.drugiprojekat.korisnickiservis.domain.Admin;
import raf.sk.drugiprojekat.korisnickiservis.repository.AdminRepository;

import java.time.LocalDate;

@Profile({"default"})
@Component
@AllArgsConstructor
public class TestDataRunner implements CommandLineRunner {
    private AdminRepository adminRepository;

    @Override
    public void run(String... args) throws Exception {
        Admin admin = new Admin();
        admin.setEmail("mmitreski8021rn@raf.rs");
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setBirthDate(LocalDate.of(2001, 10, 23));
        admin.setName("Milan");
        admin.setSurname("Mitreski");
        adminRepository.save(admin);
    }
}
