package dev.sagar.post.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a Post entity with fields for id, title, body, and userId. This class is annotated
 * with Lombok annotations to generate getters, setters, constructors, and builders. It also
 * includes JSON serialization configuration using Jackson's {@link JsonInclude} annotation.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Post {

  /** Unique identifier for the post. */
  @Id private Integer id;

  /** Title of the post. */
  private String title;

  /** Body content of the post. */
  private String body;

  /** Identifier of the user who created the post. */
  private Integer userId;
}
