package dev.sagar.album.exception;

public class AlbumNotFoundException extends RuntimeException {
  public AlbumNotFoundException(String message) {
    super(message);
  }
}
