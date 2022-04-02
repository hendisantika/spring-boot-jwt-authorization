package com.hendisantika.dto;

import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-jwt-authorization
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 02/04/22
 * Time: 19.12
 */
@Data
public class LoginDTO {
    private String username;
    private String password;
}
