package co.com.personalsoft.jwtexample.repositories;

import co.com.personalsoft.jwtexample.models.ERole;
import co.com.personalsoft.jwtexample.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
