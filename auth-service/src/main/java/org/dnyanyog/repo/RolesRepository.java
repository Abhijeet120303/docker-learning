package org.dnyanyog.repo;

import org.dnyanyog.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface RolesRepository extends JpaRepository<Roles, Long> { // Query Class
}
