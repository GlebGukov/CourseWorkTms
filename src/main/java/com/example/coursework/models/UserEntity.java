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
    @NotEmpty(message = "Name should not be empty")
    private String login;
    @Size(min = 4, max = 20, message = "Min 4 characters, max - 20")
    private String password;
    @NotEmpty(message = "please, write you first name")
    private String first_name;
    @NotEmpty(message = "please, write you last name")
    private String last_name;
    @Email
    @NotEmpty(message = "Email should not be empty")
    private String email;
    private Boolean status;
    @Enumerated(EnumType.STRING)
    private Role role;

}
