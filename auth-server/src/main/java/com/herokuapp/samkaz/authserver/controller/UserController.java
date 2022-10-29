package com.herokuapp.samkaz.authserver.controller;

import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;

@EnableResourceServer
@RestController
public class UserController {
    private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);


    @RequestMapping(value = { "/userInfo" }, produces = "application/json")
    public Map<String, Object> user(OAuth2Authentication user) {
        OAuth2Request clientAuthentication = ((OAuth2Authentication) user).getOAuth2Request();

        LOGGER.debug ("role " + user.getAuthorities());
        LOGGER.debug("scope" + clientAuthentication.getScope());

        Set<String> scope = new HashSet<>(clientAuthentication.getScope());
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("user", user.getUserAuthentication().getPrincipal());
        userInfo.put("scope", scope);
        userInfo.put("authorities", AuthorityUtils.authorityListToSet(user.getUserAuthentication().getAuthorities()));
        return userInfo;
    }
}
