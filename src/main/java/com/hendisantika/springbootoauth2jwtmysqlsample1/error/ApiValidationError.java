package com.hendisantika.springbootoauth2jwtmysqlsample1.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-oauth2-jwt-mysql-sample1
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 17/04/20
 * Time: 06.53
 */
@Data
@EqualsAndHashCode
@AllArgsConstructor
class ApiValidationError extends ApiError.ApiSubError {
    private String object;
    private String field;
    private Object rejectedValue;
    private String message;

    ApiValidationError(String object, String message) {
        this.object = object;
        this.message = message;
    }
}