package com.hendisantika.springbootoauth2jwtmysqlsample1.repository;

import com.hendisantika.springbootoauth2jwtmysqlsample1.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Override
    public Page<Message> filter(LocalDate fromDate, LocalDate toDate, LocalTime fromTime, LocalTime toTime,
                                String text, Double value, Long userId, Pageable p) {
        String query = "from Message where 1=1";
        String countQuery = "select count(*) from Message where 1=1";
        Map<String, Object> params = new HashMap<>();
        if (fromDate != null) {
            String f = " and date >= :fromDate";
            query += f;
            countQuery += f;
            params.put("fromDate", fromDate);
        }
        if (toDate != null) {
            String f = " and date <= :toDate";
            query += f;
            countQuery += f;
            params.put("toDate", toDate);
        }
        if (fromTime != null) {
            String f = " and time >= :fromTime";
            query += f;
            countQuery += f;
            params.put("fromTime", fromTime);
        }
        if (toTime != null) {
            String f = " and time <= :toTime";
            query += f;
            countQuery += f;
            params.put("toTime", toTime);
        }
        if (userId != null) {
            String f = " and user.id = :userId";
            query += f;
            countQuery += f;
            params.put("userId", userId);
        }
        if (p.getSort().isSorted()) {
            query += " order by " + p.getSort().get().map(o -> o.getProperty() + " " + o.getDirection()).collect(Collectors.joining(", "));
        }
        TypedQuery<Message> q = em.createQuery(query, Message.class);
        q.setMaxResults(p.getPageSize());
        q.setFirstResult((int) p.getOffset());
        Query c = em.createQuery(countQuery);
        params.forEach((k, v) -> {
            q.setParameter(k, v);
            c.setParameter(k, v);
        });
        List<Message> list = q.getResultList();
        Long count = (Long) c.getSingleResult();
        return new PageImpl<>(list, p, count);
    }
}
