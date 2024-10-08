package com.kostiago.backend.projections;

public interface UserDetailProjection {

    String getUsername();

    String getPassword();

    Long getPermissionId();

    String getPermission();
}
