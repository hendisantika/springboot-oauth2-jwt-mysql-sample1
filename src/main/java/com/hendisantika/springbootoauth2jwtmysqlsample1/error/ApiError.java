package com.hendisantika.springbootoauth2jwtmysqlsample1.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Created by IntelliJ IDEA. Project : springboot-oauth2-jwt-mysql-sample1 User: hendisantika Email:
 * hendisantika@gmail.com Telegram : @hendisantika34 Date: 17/04/20 Time: 06.50
 */
@Data
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.CUSTOM, property = "error", visible =
        true)
@JsonTypeIdResolver(LowerCaseClassNameResolver.class)
class ApiError {

  private HttpStatus status;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
  private LocalDateTime timestamp;
  private String message;
  private String debugMessage;
  private List<ApiSubError> subErrors;

  public ApiError(HttpStatus badRequest) {
  }
}
