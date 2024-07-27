package dev.sagar.post.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

/**
 * A global exception handler for the application. This class is annotated with {@link
 * ControllerAdvice} to make it a global exception handler. It handles exceptions thrown by the
 * controllers and provides appropriate responses.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

  /**
   * Handles {@link PostNotFoundException} exceptions. It sets the response status to 404 (Not
   * Found) and constructs a response body with details about the exception.
   *
   * @param ex The {@link PostNotFoundException} that occurred.
   * @param request The {@link WebRequest} that triggered the exception.
   * @return A {@link ResponseEntity} containing the constructed response body and status.
   */
  @ExceptionHandler(PostNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<Map<String, Object>> handlePostNotFoundException(
      PostNotFoundException ex, WebRequest request) {

    Map<String, Object> body = new HashMap<>();
    body.put("timestamp", LocalDateTime.now());
    body.put("status", HttpStatus.NOT_FOUND.value());
    body.put("error", "Not Found");
    body.put("message", ex.getMessage());
    body.put("path", request.getDescription(false));

    return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
  }

  /**
   * Handles all other exceptions. It sets the response status to 500 (Internal Server Error) and
   * constructs a response body with details about the exception.
   *
   * @param ex The {@link Exception} that occurred.
   * @param request The {@link WebRequest} that triggered the exception.
   * @return A {@link ResponseEntity} containing the constructed response body and status.
   */
  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<Map<String, Object>> handleGlobalException(
      Exception ex, WebRequest request) {

    Map<String, Object> body = new HashMap<>();
    body.put("timestamp", LocalDateTime.now());
    body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
    body.put("error", "Internal Server Error");
    body.put("message", ex.getMessage());
    body.put("path", request.getDescription(false));

    return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
