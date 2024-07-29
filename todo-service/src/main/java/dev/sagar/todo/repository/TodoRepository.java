package dev.sagar.todo.repository;

import dev.sagar.todo.model.Todo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {
  List<Todo> findByUserId(Integer userId);
}
