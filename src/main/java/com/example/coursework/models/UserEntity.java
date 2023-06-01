package com.example.coursework.models;

import com.example.coursework.security.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor


@Entity(name = "users")
public class UserEntity {

    @Id
    private UUID id;
    private String login;
    private String password;
    private String first_name;
    private String last_name;
    private String email;
    private Boolean status;
    @Enumerated(EnumType.STRING)
    private Role role;

}
