package com.stefanyshyn.airbnbcloneback.infrastructure.config;

import com.stefanyshyn.airbnbcloneback.user.domain.Authority;
import com.stefanyshyn.airbnbcloneback.user.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SecurityUtils {

    public static final String ROLE_TENANT = "ROLE_TENANT";
    public static final String ROLE_LANDLORD = "ROLE_LANDLORD";

    public static final String CLAIMS_NAMESPACE = "http://stefanyshyn.com/roles";

    /**
     * Витягує GrantedAuthority з claims.
     */
    public static Collection<GrantedAuthority> extractAuthorityFromClaims(Map<String, Object> claims) {
        System.out.println("Extracting authorities from claims: " + claims);

        Object rolesObject = claims.get(CLAIMS_NAMESPACE);

        if (rolesObject instanceof Collection<?> roles) {
            System.out.println("Roles found: " + roles);

            return roles.stream()
                    .filter(role -> role instanceof String) // Перевірка, що це String
                    .map(role -> new SimpleGrantedAuthority((String) role))
                    .collect(Collectors.toSet()); // Використання Set для уникнення дублікатів
        }

        System.out.println("No roles found in claims or invalid type. Roles object: " + rolesObject);
        return Set.of(); // Повертаємо порожній набір, якщо ролі відсутні або тип некоректний
    }

    /**
     * Мапить ролі на GrantedAuthority.
     */
    public static Collection<GrantedAuthority> mapRolesToGrantedAuthorities(Collection<String> roles) {
        if (roles == null || roles.isEmpty()) {
            System.out.println("No roles provided, returning empty authority list.");
            return Set.of();
        }

        System.out.println("Mapping roles to authorities: " + roles);

        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }

    /**
     * Створює користувача на основі атрибутів OAuth2.
     */
    public static User mapOauth2AttributesToUser(Map<String, Object> attributes) {
        User user = new User();
        String sub = String.valueOf(attributes.get("sub"));

        String username = null;

        if (attributes.get("preferred_username") != null) {
            username = ((String) attributes.get("preferred_username")).toLowerCase();
        }

        if (attributes.get("given_name") != null) {
            user.setFirstName(((String) attributes.get("given_name")));
        } else if ((attributes.get("nickname") != null)) {
            user.setFirstName(((String) attributes.get("nickname")));
        }

        if (attributes.get("family_name") != null) {
            user.setLastName(((String) attributes.get("family_name")));
        }

        if (attributes.get("email") != null) {
            user.setEmail(((String) attributes.get("email")));
        } else if (sub.contains("|") && (username != null && username.contains("@"))) {
            user.setEmail(username);
        } else {
            user.setEmail(sub);
        }

        if (attributes.get("picture") != null) {
            user.setImageUrl(((String) attributes.get("picture")));
        }

        if (attributes.get(CLAIMS_NAMESPACE) != null) {
            List<String> authoritiesRaw = (List<String>) attributes.get(CLAIMS_NAMESPACE);
            Set<Authority> authorities = authoritiesRaw.stream()
                    .map(authority -> {
                        Authority auth = new Authority();
                        auth.setName(authority);
                        return auth;
                    }).collect(Collectors.toSet());
            user.setAuthorities(authorities);
        }

        return user;
    }

    /**
     * Перевіряє, чи поточний користувач має хоча б одну з указаних авторизацій.
     */
    public static boolean hasCurrentUserAnyOfAuthorities(String... authorities) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (authentication != null && getAuthorities(authentication)
                .anyMatch(authority -> Arrays.asList(authorities).contains(authority)));
    }

    private static Stream<String> getAuthorities(Authentication authentication) {
        Collection<? extends GrantedAuthority> authorities = authentication instanceof JwtAuthenticationToken jwtAuthenticationToken
                ? extractAuthorityFromClaims(jwtAuthenticationToken.getToken().getClaims())
                : authentication.getAuthorities();
        return authorities.stream().map(GrantedAuthority::getAuthority);
    }
}