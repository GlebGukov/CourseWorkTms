package com.example.coursework.dto;

import com.example.coursework.models.CommentsEntity;
import com.example.coursework.security.Role;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserDto {

    private UUID id;
    private String login;
    private String password;
    private String first_name;
    private String last_name;
    @NotNull
    private String email;
    private Boolean status = true;
    private Role role;
    @JsonManagedReference
    private List<CommentsDto> comments;

}
