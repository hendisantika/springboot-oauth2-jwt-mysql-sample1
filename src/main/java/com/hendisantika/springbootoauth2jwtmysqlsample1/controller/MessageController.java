package com.hendisantika.springbootoauth2jwtmysqlsample1.controller;

import com.hendisantika.springbootoauth2jwtmysqlsample1.error.EntityNotFoundException;
import com.hendisantika.springbootoauth2jwtmysqlsample1.model.Message;
import com.hendisantika.springbootoauth2jwtmysqlsample1.model.User;
import com.hendisantika.springbootoauth2jwtmysqlsample1.repository.MessageRepository;
import com.hendisantika.springbootoauth2jwtmysqlsample1.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;

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

    @GetMapping
    Page<Message> allMessages(Pageable pageable, OAuth2Authentication authentication) {
        String auth = (String) authentication.getUserAuthentication().getPrincipal();
        String role = authentication.getAuthorities().iterator().next().getAuthority();
        if (role.equals(User.Role.USER.name())) {
            User user = userRepository.findByEmail(auth).orElseThrow(() -> new EntityNotFoundException(User.class,
                    "email", auth));
            return messageRepository.findAllByUser(user, pageable);
        }
        return messageRepository.findAll(pageable);
    }

    @GetMapping("/search")
    Page<Message> searchMessages(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @RequestParam(value = "fromDate", required = false) LocalDate fromDate,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @RequestParam(value = "toDate", required = false) LocalDate toDate,
            @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
            @RequestParam(value = "fromTime", required = false) LocalTime fromTime,
            @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
            @RequestParam(value = "toTime", required = false) LocalTime toTime,
            @RequestParam(value = "text", required = false) String text,
            @RequestParam(value = "value", required = false) Double cal,
            @RequestParam(value = "userId", required = false) Long userId,
            Pageable pageable, OAuth2Authentication authentication) {
        String auth = (String) authentication.getUserAuthentication().getPrincipal();
        String role = authentication.getAuthorities().iterator().next().getAuthority();
        if (role.equals(User.Role.USER.name())) {
            User user = userRepository.findByEmail(auth).orElseThrow(() -> new EntityNotFoundException(User.class,
                    "email", auth));
            userId = user.getId();
            return messageRepository.filter(fromDate, toDate, fromTime, toTime, text, cal, userId, pageable);
        }
        return messageRepository.filter(fromDate, toDate, fromTime, toTime, text, cal, userId, pageable);
    }

}
