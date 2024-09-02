package org.spring.sec.repository;

import org.spring.sec.entity.Authority;
import org.spring.sec.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Optional<Authority> findByName(String name);
}
