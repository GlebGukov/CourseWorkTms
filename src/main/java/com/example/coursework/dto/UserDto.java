package com.example.coursework.dto;

import com.example.coursework.security.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserDto {

    private UUID id;
    @NotEmpty(message = "Name should not be empty")
    private String login;
    @Size(min = 4, max = 20, message = "Password: Min 4 characters, max - 20")
    private String password;
    @NotEmpty(message = "please, write you first name")
    private String first_name;
    @NotEmpty(message = "please, write you last name")
    private String last_name;
    @Email(message = "please check in correct you email ")
    private String email;
    private Boolean status = true;
    private Role role = Role.ROLE_USER;
    private List<CommentsDto> comments;

}
