package kata9.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.stream.Collectors;

class HeaderUtils {
    static String getUserName(Authentication authentication) {
        return authentication.getName();
    }
    static String getRoles(Authentication authentication) {
        return authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(", "));
    }
}
