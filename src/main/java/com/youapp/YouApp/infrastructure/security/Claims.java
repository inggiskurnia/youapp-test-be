package com.youapp.YouApp.infrastructure.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Map;

public class Claims {

    public static Map<String, Object> getClaimsFromJwt(){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof Jwt jwt)){
            throw new IllegalStateException("JWT not found in security context");
        }
        return jwt.getClaims();
    }

    public static String getEmailFromJwt(){
        return (String) getClaimsFromJwt().get("sub");
    }

    public static String getExpirationDateFromJwt(){
        return (String) getClaimsFromJwt().get("exp").toString();
    }

    public static String getJwtTokenString(){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof Jwt jwt)){
            throw new IllegalStateException("JWT not found in security context");
        }
        return jwt.getTokenValue();
    }

    public static String getTokenTypeFromJwt() {
        Object claim = getClaimsFromJwt().get("tokenType");
        return claim != null ? claim.toString() : null;
    }

    public static Long getUserIdFromJwt(){
        Object userId = getClaimsFromJwt().get("userId");
        if (userId instanceof Integer){
            return ((Integer) userId).longValue();
        }
        else if (userId instanceof Long){
            return (Long) userId;
        }
        else if (userId instanceof String){
            return Long.parseLong((String) userId);
        }
        throw new IllegalStateException("User Id not found !");
    }
}
