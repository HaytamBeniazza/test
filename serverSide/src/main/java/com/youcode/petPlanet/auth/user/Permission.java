package com.youcode.petPlanet.auth.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    BLOGAUTHOR_READ("blogAuthor:read"),
    BLOGAUTHOR_UPDATE("blogAuthor:update"),
    BLOGAUTHOR_CREATE("blogAuthor:create"),
    BLOGAUTHOR_DELETE("blogAuthor:delete"),
    CLIENT_READ("client:read"),
    CLIENT_UPDATE("client:update"),
    CLIENT_CREATE("client:create"),
    CLIENT_DELETE("client:delete");

    @Getter
    private final String permission;
}
