package cn.huanuo.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

@Component
public class CustomAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {

        if (collection == null)
            return;

        Iterator<ConfigAttribute> ite = collection.iterator();
        while (ite.hasNext()) {

            ConfigAttribute attribute = ite.next();
            String rolename = attribute.getAttribute();

            for (GrantedAuthority ga : authentication.getAuthorities()) {
                if (rolename.trim().equals(ga.getAuthority().trim()))
                    return;
            }

        }

        throw new AccessDeniedException("Insufficient permissions");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
