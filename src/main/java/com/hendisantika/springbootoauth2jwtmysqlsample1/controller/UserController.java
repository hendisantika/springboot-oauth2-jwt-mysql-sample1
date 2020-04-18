package com.hendisantika.springbootoauth2jwtmysqlsample1.controller;

import com.hendisantika.springbootoauth2jwtmysqlsample1.error.EntityNotFoundException;
import com.hendisantika.springbootoauth2jwtmysqlsample1.model.User;
import com.hendisantika.springbootoauth2jwtmysqlsample1.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-oauth2-jwt-mysql-sample1
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 18/04/20
 * Time: 16.14
 */
@RestController
@RequestMapping("/api/users")
@Slf4j
@Validated
class UserController {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    UserController(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    Page<User> all(@PageableDefault(size = Integer.MAX_VALUE) Pageable pageable, OAuth2Authentication authentication) {
        String auth = (String) authentication.getUserAuthentication().getPrincipal();
        String role = authentication.getAuthorities().iterator().next().getAuthority();
        if (role.equals(User.Role.USER.name())) {
            return repository.findAllByEmail(auth, pageable);
        }
        return repository.findAll(pageable);
    }

    @GetMapping("/search")
    Page<User> search(@RequestParam String email, Pageable pageable, OAuth2Authentication authentication) {
        String auth = (String) authentication.getUserAuthentication().getPrincipal();
        String role = authentication.getAuthorities().iterator().next().getAuthority();
        if (role.equals(User.Role.USER.name())) {
            return repository.findAllByEmailContainsAndEmail(email, auth, pageable);
        }
        return repository.findByEmailContains(email, pageable);
    }

    @GetMapping("/findByEmail")
    @PreAuthorize("!hasAuthority('USER') || (authentication.principal == #email)")
    User findByEmail(@RequestParam String email, OAuth2Authentication authentication) {
        return repository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException(User.class, "email", email));
    }

}
