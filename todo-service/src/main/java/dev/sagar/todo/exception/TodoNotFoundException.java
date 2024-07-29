package dev.sagar.todo.exception;

public class TodoNotFoundException extends RuntimeException {
  public TodoNotFoundException(String message) {
    super(message);
  }
}
