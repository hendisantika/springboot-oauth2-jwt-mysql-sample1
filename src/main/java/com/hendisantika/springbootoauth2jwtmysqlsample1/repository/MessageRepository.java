package com.hendisantika.springbootoauth2jwtmysqlsample1.repository;

import com.hendisantika.springbootoauth2jwtmysqlsample1.model.Message;
import com.hendisantika.springbootoauth2jwtmysqlsample1.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-oauth2-jwt-mysql-sample1
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 17/04/20
 * Time: 06.38
 */
public interface MessageRepository extends PagingAndSortingRepository<Message, Long> {
    Page<Message> findAllByUser(User user, Pageable pageable);
}
