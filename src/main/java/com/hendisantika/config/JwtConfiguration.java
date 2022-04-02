package com.hendisantika.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-jwt-authorization
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 02/04/22
 * Time: 18.38
 */
@Slf4j
@Configuration
public class JwtConfiguration {

    @Value("${app.security.jwt.keystore-location}")
    private String keyStorePath;

    @Value("${app.security.jwt.keystore-password}")
    private String keyStorePassword;

    @Value("${app.security.jwt.key-alias}")
    private String keyAlias;

    @Value("${app.security.jwt.private-key-passphrase}")
    private String privateKeyPassphrase;
}
