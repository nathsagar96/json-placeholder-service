package dev.sagar.photo.exception;

public class PhotoNotFoundException extends RuntimeException {
  public PhotoNotFoundException(String message) {
    super(message);
  }
}
