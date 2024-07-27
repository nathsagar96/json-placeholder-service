package dev.sagar.post.exception;

/**
 * Custom exception class for when a post is not found. This exception is thrown when a post with a
 * specific identifier is not found in the system.
 */
public class PostNotFoundException extends RuntimeException {

  /**
   * Constructs a new PostNotFoundException with the specified detail message.
   *
   * @param message the detail message (which is saved for later retrieval by the {@link
   *     #getMessage()} method).
   */
  public PostNotFoundException(String message) {
    super(message);
  }
}
