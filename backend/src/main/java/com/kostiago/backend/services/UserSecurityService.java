package com.kostiago.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Service;

import com.kostiago.backend.entities.User;
import com.kostiago.backend.entities.enums.UserSituation;
import com.kostiago.backend.services.exceptions.ForbiddenException;

@Service
public class UserSecurityService {

    @Autowired
    private UserDetailService service;

    public void validateSelfOrAdmin(long userId) {

        User me = service.authenticated();
        if (!me.hasPermission("ROLE_ADMIN") && !me.getId().equals(userId)) {
            throw new ForbiddenException("Access denied");
        }
    }

    public void isUserPending(long userId) {
        User me = service.authenticated();
        if (!me.hasSituation(UserSituation.ATIVO)
                || (!me.hasPermission("ROLE_ADMIN") && !me.getId().equals(userId))) {
            throw new ForbiddenException("Access denied, active your account");
        }
    }
}
