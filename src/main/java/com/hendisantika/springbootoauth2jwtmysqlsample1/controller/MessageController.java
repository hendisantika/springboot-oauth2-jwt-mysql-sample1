package com.hendisantika.springbootoauth2jwtmysqlsample1.controller;

import com.hendisantika.springbootoauth2jwtmysqlsample1.repository.MessageRepository;
import com.hendisantika.springbootoauth2jwtmysqlsample1.repository.UserRepository;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-oauth2-jwt-mysql-sample1
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 19/04/20
 * Time: 13.57
 */
@RestController
@RequestMapping("/api/messages")
@Validated
public class MessageController {
    private final MessageRepository messageRepository;

    private final UserRepository userRepository;

    MessageController(MessageRepository repository, UserRepository userRepository) {
        this.messageRepository = repository;
        this.userRepository = userRepository;
    }
}
