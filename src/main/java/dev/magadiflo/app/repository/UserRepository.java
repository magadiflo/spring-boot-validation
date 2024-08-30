package dev.magadiflo.app.repository;

import dev.magadiflo.app.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
