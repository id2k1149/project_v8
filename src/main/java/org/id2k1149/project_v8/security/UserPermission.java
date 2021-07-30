package org.id2k1149.project_v8.security;

public enum UserPermission {
    READ("student:read"),
    WRITE("student:write")
   ;

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
