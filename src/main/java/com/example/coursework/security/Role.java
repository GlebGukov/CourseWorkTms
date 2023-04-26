package com.example.coursework.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    ROLE_USER(Set.of(Permission.READ)),
    ROLE_ADMIN(Set.of(Permission.READ, Permission.WRITE)),
    ROLE_OWNER(Set.of(Permission.READ, Permission.WRITE, Permission.CREATE));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissionSet) {
        this.permissions = permissionSet;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
