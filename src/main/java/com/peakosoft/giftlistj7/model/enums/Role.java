package com.peakosoft.giftlistj7.model.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN,
    USER,
    BLOCKED;
    @Override
    public String getAuthority() {
       return name();
    }
}
