package org.id2k1149.project_v8.security;

import com.google.common.collect.Sets;

import java.util.Set;

import static org.id2k1149.project_v8.security.UserPermission.*;

public enum UserRole {
    USER(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(USER_READ,
                        USER_WRITE,
                        QUESTION_READ,
                        QUESTION_WRITE));

    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions() {
        return permissions;
    }
}
