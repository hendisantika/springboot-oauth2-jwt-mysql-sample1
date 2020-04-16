package com.hendisantika.springbootoauth2jwtmysqlsample1.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-oauth2-jwt-mysql-sample1
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 17/04/20
 * Time: 06.43
 */
@Configuration
@Slf4j
public class LoadDatabase {

    @Value("${data.users:admin,userman,user1,user2,user3,user4,user5}")
    private String[] users;

    @Value("${data.nouns:sky,winter,sun,moon,spring,weekend,fall,summer,mountain,wolf,bird}")
    private String[] nouns;

    @Value("${data.verbs:falling,rising,exploding,shining,coming,lightning,howling,chirping}")
    private String[] verbs;

    @Value("${data.feelings:bad,numb,fine,good}")
    private String[] feelings;

    @Value("${data.timeOfDay:morning,afternoon,evening,night}")
    private String[] timeOfDay;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("clientSecret:secret")
    private String clientSecret;

}
