package dev.sagar.user.repository;

import dev.sagar.user.model.Company;
import io.micrometer.observation.annotation.Observed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Observed
@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
}
