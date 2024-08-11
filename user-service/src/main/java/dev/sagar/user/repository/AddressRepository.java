package dev.sagar.user.repository;

import dev.sagar.user.model.Address;
import io.micrometer.observation.annotation.Observed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Observed
@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
}
