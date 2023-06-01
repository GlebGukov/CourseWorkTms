package com.example.coursework.security;

public enum Permission {

    READ("read"),
    WRITE("write"),
    CREATE("create");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
