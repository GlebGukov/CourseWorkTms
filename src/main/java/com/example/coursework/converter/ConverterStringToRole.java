package com.example.coursework.converter;

import com.example.coursework.security.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Data
@NoArgsConstructor

@Service
public class ConverterStringToRole {

    public Role converterStringToRole(String role) {
        return Role.valueOf(Role.class, role);
    }
}
