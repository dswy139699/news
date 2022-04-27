package com.news.manage.moudle.news.Config.jwt;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

public final class SecurityUtil {

    public static String getUserId(){
        SecurityContext context = SecurityContextHolder.getContext();
        if(Objects.nonNull(context) && Objects.nonNull(context.getAuthentication())){
            String principal = (String) context.getAuthentication().getPrincipal();
            if(Objects.nonNull(principal) && !"".equals(principal)){
                return principal;
            }
        }
        return null;
    }
}
