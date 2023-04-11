package com.example.coursework.repositories;

import com.example.coursework.models.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;


public interface UserRepository extends CrudRepository<UserEntity, UUID> {

    UserEntity findByLogin(String login);

}
