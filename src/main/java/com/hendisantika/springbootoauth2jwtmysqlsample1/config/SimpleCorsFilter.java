package com.hendisantika.springbootoauth2jwtmysqlsample1.config;

import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-oauth2-jwt-mysql-sample1
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 18/04/20
 * Time: 16.04
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@Profile("dev")
public class SimpleCorsFilter implements Filter {

    public SimpleCorsFilter() {
    }
}
