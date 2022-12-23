package com.tesla.generalmodule;

import com.tesla.model.User;
import com.tesla.model.UserType;
import com.tesla.reposervicesecurity.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@EnableJpaRepositories(basePackages = {"com.tesla.reposervicesecurity.repo"})
@EntityScan(value = {"com.tesla.model"})
@ComponentScan(value = {"com.tesla.generalmodule", "com.tesla.model", "com.tesla.reposervicesecurity"})
@SpringBootApplication
public class GeneralModuleApplication implements CommandLineRunner {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(GeneralModuleApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepo.findByEmail("narek@nm").isEmpty()) {
            userRepo.save(User.builder()
                    .name("Narek")
                    .surname("Martirosyan")
                    .email("narek@nm")
                    .localDate(LocalDate.parse("2022-09-25"))
                    .userType(UserType.ADMIN)
                    .password(passwordEncoder.encode("077113162"))
                    .build());
        }
    }
}
