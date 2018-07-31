package com.shortener.util;

import com.shortener.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public interface UserUtil {

    static User getUser() {
        final SecurityContext ctx = SecurityContextHolder.getContext();

        if (ctx != null) {
            final Authentication auth = ctx.getAuthentication();

            if (auth != null) {
                final Object principal = auth.getPrincipal();

                if (principal instanceof User) {

                    return (User) principal;
                }
            }
        }
        return null;
    }

}
