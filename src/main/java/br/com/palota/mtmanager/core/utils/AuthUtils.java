package br.com.palota.mtmanager.core.utils;

import br.com.palota.mtmanager.domain.model.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthUtils {

    public static User getAuthenticated() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
