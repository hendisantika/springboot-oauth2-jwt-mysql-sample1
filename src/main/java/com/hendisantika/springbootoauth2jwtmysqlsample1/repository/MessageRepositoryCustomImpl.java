package com.hendisantika.springbootoauth2jwtmysqlsample1.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-oauth2-jwt-mysql-sample1
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 17/04/20
 * Time: 06.41
 */
@Repository
@Transactional(readOnly = true)
public class MessageRepositoryCustomImpl implements MessageRepositoryCustom {
    @PersistenceContext
    private EntityManager em;
}
