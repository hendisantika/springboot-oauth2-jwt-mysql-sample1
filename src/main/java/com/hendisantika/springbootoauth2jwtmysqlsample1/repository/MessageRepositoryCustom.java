package com.hendisantika.springbootoauth2jwtmysqlsample1.repository;

import com.hendisantika.springbootoauth2jwtmysqlsample1.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-oauth2-jwt-mysql-sample1
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 17/04/20
 * Time: 06.40
 */
public interface MessageRepositoryCustom {
    Page<Message> filter(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @Param("fromDate") LocalDate fromDate,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @Param("toDate") LocalDate toDate,
            @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
            @Param("fromTime") LocalTime fromTime,
            @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
            @Param("toTime") LocalTime toTime,
            @Param("text") String text,
            @Param("value") Double value,
            @Param("userId") Long userId,
            Pageable p);
}
