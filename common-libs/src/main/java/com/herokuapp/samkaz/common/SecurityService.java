package com.herokuapp.samkaz.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.LinkedHashMap;

@Service
public class SecurityService {
    Logger logger = LoggerFactory.getLogger(SecurityService.class);


    public boolean hasScope(String[] scopes){
        OAuth2Authentication authentication = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
        logger.debug(String.valueOf(authentication));
        if (authentication instanceof OAuth2Authentication) {
            Collection<String> containedScopes = (Collection<String>) ((LinkedHashMap<String, Object>) authentication.getUserAuthentication().getDetails()).get("scope");
            logger.debug(String.valueOf(containedScopes));
            Collection<String> assigned = containedScopes;
            if (assigned != null) {
                String[] var4 = scopes;
                int var5 = scopes.length;
                for(int var6 = 0; var6 < var5; ++var6) {
                    String scope = var4[var6];
                    if (assigned.contains(scope)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}