package com.bravo.demo.ssoclient2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableOAuth2Sso
@SpringBootApplication
public class SpringSecuritySsoClient2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecuritySsoClient2Application.class, args);
    }

    @GetMapping("/user")
    public Authentication getCurrentUser(Authentication user){
        return user;
    }
}
