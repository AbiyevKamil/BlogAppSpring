package com.kamilabiyev.blog.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ConfigurationProperties(prefix = "jwt.config")
public class JWTTokenProperties {
    private String secretKey;
    private Integer expirationTime;
}
