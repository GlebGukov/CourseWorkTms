package com.example.coursework.repositories;

import com.example.coursework.models.UserEntity;
import com.example.coursework.security.Role;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.UUID;



@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void saveUserFromDataBase(){
        UserEntity userEntity = userEntity();
        userRepository.save(userEntity);
        Iterable<UserEntity> all = userRepository.findAll();
        Assertions.assertThat(all).hasSize(1);
    }

    @Test
    void findByLogin() {
    }

    @Test
    void findAllByIdNotNull() {
    }
    private static UserEntity userEntity(){
        return UserEntity.builder()
                .id(UUID.randomUUID())
                .login("testLogin")
                .email("test@email.test")
                .first_name("testName")
                .last_name("lastName")
                .password("test666")
                .role(Role.ROLE_USER)
                .status(true)
                .build();
    }
}