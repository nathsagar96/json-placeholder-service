package dev.sagar.todo.repository;

import dev.sagar.todo.model.Todo;
import io.micrometer.observation.annotation.Observed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Observed
@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {
    List<Todo> findByUserId(Integer userId);
}
