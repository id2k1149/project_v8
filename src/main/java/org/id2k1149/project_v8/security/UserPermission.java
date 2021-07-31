package org.id2k1149.project_v8.security;

public enum UserPermission {
    USER_READ("user:read"),
    USER_WRITE("user:write"),
    QUESTION_READ("question:read"),
    QUESTION_WRITE("question:write");

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
