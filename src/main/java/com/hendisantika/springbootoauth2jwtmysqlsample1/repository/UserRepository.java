package com.hendisantika.springbootoauth2jwtmysqlsample1.repository;

import com.hendisantika.springbootoauth2jwtmysqlsample1.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-oauth2-jwt-mysql-sample1
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 17/04/20
 * Time: 06.44
 */
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
}
