package dev.sagar.user.repository;

import dev.sagar.user.model.User;
import io.micrometer.observation.annotation.Observed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Observed
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
