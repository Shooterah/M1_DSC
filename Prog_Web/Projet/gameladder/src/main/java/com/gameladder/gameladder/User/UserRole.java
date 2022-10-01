package com.gameladder.gameladder.User;

import org.springframework.security.core.GrantedAuthority;

/**
 * UserRole
 */
public enum UserRole implements GrantedAuthority {   // Permet de mettre des roles pour la sécutiré
                                                    // Et pour l'acces a la DB et autres
    USER,
    MODERATEUR,
    ADMIN;

    @Override
    public String getAuthority() {
        return "ROLE_"+this.name();
    }
}
